(() => {
  const state = {
    token: localStorage.getItem("partyhub_token") || "",
    username: localStorage.getItem("partyhub_username") || "游客",
  };

  const els = {
    topStatus: document.getElementById("top-status"),
    welcomeUser: document.getElementById("welcome-user"),
    loginForm: document.getElementById("login-form"),
    usernameInput: document.getElementById("username"),
    passwordInput: document.getElementById("password"),
    loginStatus: document.getElementById("login-status"),
    logoutBtn: document.getElementById("logout-btn"),
    quickLoginBtn: document.getElementById("quick-login"),
    refreshBtn: document.getElementById("refresh-btn"),
    yearSelect: document.getElementById("year-select"),
    metricsGrid: document.getElementById("metrics-grid"),
    ratingBars: document.getElementById("rating-bars"),
    processBars: document.getElementById("process-bars"),
    rankingBody: document.getElementById("ranking-body"),
    trendList: document.getElementById("trend-list"),
    dashboardHint: document.getElementById("dashboard-hint"),
    hero: document.getElementById("hero"),
  };

  const defaultOverview = {
    totalRecords: 0,
    submittedRecords: 0,
    completedRecords: 0,
    avgScore: 0,
    fiveStarCount: 0,
    fourStarCount: 0,
    threeStarCount: 0,
  };

  function setLoginStatus(message, type) {
    if (!els.loginStatus) return;
    els.loginStatus.className = "login-status" + (type ? ` ${type}` : "");
    els.loginStatus.textContent = message || "";
  }

  function setTopStatus() {
    if (!els.topStatus) return;
    if (state.token) {
      els.topStatus.textContent = `已登录：${state.username}`;
    } else {
      els.topStatus.textContent = "未登录（可用演示账号）";
    }
    if (els.welcomeUser) {
      els.welcomeUser.textContent = state.token ? state.username : "请先登录查看实时数据";
    }
  }

  function buildQuery(params) {
    const query = new URLSearchParams();
    Object.keys(params).forEach((key) => {
      const value = params[key];
      if (value !== undefined && value !== null && value !== "") {
        query.append(key, String(value));
      }
    });
    const text = query.toString();
    return text ? `?${text}` : "";
  }

  async function apiRequest(path, options = {}, authRequired = false) {
    const headers = Object.assign({}, options.headers || {});
    if (options.body !== undefined) {
      headers["Content-Type"] = "application/json";
    }

    if (authRequired && state.token) {
      headers.Authorization = `Bearer ${state.token}`;
    }

    const response = await fetch(path, {
      method: options.method || "GET",
      headers,
      body: options.body !== undefined ? JSON.stringify(options.body) : undefined,
    });

    let payload = null;
    try {
      payload = await response.json();
    } catch (err) {
      payload = null;
    }

    if (!response.ok) {
      const errorMessage = payload && payload.message ? payload.message : `请求失败（${response.status}）`;
      const error = new Error(errorMessage);
      error.status = response.status;
      throw error;
    }

    if (!payload || payload.code !== "OK") {
      const code = payload && payload.code ? payload.code : "UNKNOWN";
      const message = payload && payload.message ? payload.message : "接口返回异常";
      const error = new Error(`${message} [${code}]`);
      error.status = response.status;
      throw error;
    }

    return payload.data;
  }

  function renderMetrics(overview) {
    const data = Object.assign({}, defaultOverview, overview || {});
    const metricItems = [
      ["评星总记录", data.totalRecords],
      ["已提交流程", data.submittedRecords],
      ["已完成审核", data.completedRecords],
      ["平均分", Number(data.avgScore || 0).toFixed(2)],
    ];

    els.metricsGrid.innerHTML = metricItems
      .map(
        ([label, value]) => `
        <article class="metric reveal">
          <div class="metric-label">${label}</div>
          <div class="metric-value">${value}</div>
        </article>`
      )
      .join("");

    triggerReveal();
  }

  function renderBarGroup(container, list, emptyText) {
    if (!Array.isArray(list) || list.length === 0) {
      container.innerHTML = `<p class="section-note">${emptyText}</p>`;
      return;
    }

    const max = Math.max(...list.map((item) => Number(item.value || 0)), 1);

    container.innerHTML = `<div class="bar-list">${list
      .map((item, idx) => {
        const label = item.label || item.name || item.key || `项 ${idx + 1}`;
        const value = Number(item.value || 0);
        const width = Math.max(8, Math.round((value / max) * 100));
        return `
          <div class="bar-item reveal">
            <span class="bar-name">${label}</span>
            <div class="bar-track"><span class="bar-fill" style="width:${width}%"></span></div>
            <span class="bar-num">${value}</span>
          </div>`;
      })
      .join("")}</div>`;

    triggerReveal();
  }

  function renderRanking(rows) {
    if (!Array.isArray(rows) || rows.length === 0) {
      els.rankingBody.innerHTML = '<tr><td colspan="5">暂无排名数据</td></tr>';
      return;
    }

    els.rankingBody.innerHTML = rows
      .map((row, index) => {
        const avgScore = Number(row.avgScore || 0).toFixed(2);
        const completion = Number(row.completedRate || 0).toFixed(1);
        return `
          <tr class="reveal">
            <td class="td-rank">#${index + 1}</td>
            <td>${row.deptName || "未命名学院"}</td>
            <td>${row.recordCount || 0}</td>
            <td class="td-score">${avgScore}</td>
            <td class="td-rate">${completion}%</td>
          </tr>`;
      })
      .join("");

    triggerReveal();
  }

  function renderTrend(rows) {
    if (!Array.isArray(rows) || rows.length === 0) {
      els.trendList.innerHTML = '<p class="section-note">暂无年度趋势</p>';
      return;
    }

    els.trendList.innerHTML = rows
      .map((row) => {
        const score = Number(row.avgScore || 0).toFixed(2);
        return `
          <div class="trend-chip reveal">
            <div class="trend-year">${row.year || "未标注年份"}</div>
            <div class="trend-score">${score}</div>
            <div>记录 ${row.totalRecords || 0}</div>
          </div>`;
      })
      .join("");

    const selectedYear = els.yearSelect.value;
    const years = rows
      .map((item) => item.year)
      .filter((year) => year && /^\d{4}$/.test(year))
      .sort();

    const options = ["<option value=''>全部年份</option>"];
    years.forEach((year) => {
      const selected = selectedYear === year ? " selected" : "";
      options.push(`<option value="${year}"${selected}>${year} 年</option>`);
    });
    els.yearSelect.innerHTML = options.join("");

    triggerReveal();
  }

  async function loadDashboard() {
    if (!state.token) {
      renderMetrics(defaultOverview);
      renderBarGroup(els.ratingBars, [], "登录后可查看星级分布");
      renderBarGroup(els.processBars, [], "登录后可查看流程分布");
      renderRanking([]);
      renderTrend([]);
      els.dashboardHint.textContent = "当前为访客模式。使用右侧登录框体验真实统计数据。";
      return;
    }

    const year = els.yearSelect.value;
    const yearQuery = buildQuery({ year });
    const rankingQuery = buildQuery({ year, limit: 8 });

    try {
      els.dashboardHint.textContent = "正在同步统计数据...";
      const [overview, rating, process, ranking, trend] = await Promise.all([
        apiRequest(`/api/stars/stats/overview${yearQuery}`, {}, true),
        apiRequest(`/api/stars/stats/rating-distribution${yearQuery}`, {}, true),
        apiRequest(`/api/stars/stats/process-distribution${yearQuery}`, {}, true),
        apiRequest(`/api/stars/stats/dept-ranking${rankingQuery}`, {}, true),
        apiRequest("/api/stars/stats/trend", {}, true),
      ]);

      renderMetrics(overview);
      renderBarGroup(els.ratingBars, rating, "当前年份暂无评级分布");
      renderBarGroup(els.processBars, process, "当前年份暂无流程分布");
      renderRanking(ranking);
      renderTrend(trend);
      els.dashboardHint.textContent = "数据已更新。可切换年份筛选查看不同周期表现。";
    } catch (error) {
      if (error.status === 401 || error.status === 403) {
        logout();
        setLoginStatus("登录状态已过期，请重新登录。", "error");
        return;
      }

      els.dashboardHint.textContent = `数据读取失败：${error.message}`;
      setLoginStatus(`统计接口异常：${error.message}`, "error");
    }
  }

  async function login(event) {
    event.preventDefault();

    const username = (els.usernameInput.value || "").trim();
    const password = (els.passwordInput.value || "").trim();

    if (!username || !password) {
      setLoginStatus("请输入用户名和密码。", "error");
      return;
    }

    setLoginStatus("正在验证身份...", "");

    try {
      const data = await apiRequest(
        "/api/auth/login",
        {
          method: "POST",
          body: { username, password },
        },
        false
      );

      state.token = data.token;
      state.username = data.username || username;
      localStorage.setItem("partyhub_token", state.token);
      localStorage.setItem("partyhub_username", state.username);
      localStorage.setItem(
        "loginInfo",
        JSON.stringify({
          userName: state.username,
          nickName: state.username,
          token: state.token,
        })
      );

      setTopStatus();
      setLoginStatus("登录成功，正在加载看板数据。", "success");
      await loadDashboard();
    } catch (error) {
      setLoginStatus(error.message || "登录失败", "error");
    }
  }

  function logout() {
    state.token = "";
    state.username = "游客";
    localStorage.removeItem("partyhub_token");
    localStorage.removeItem("partyhub_username");
    localStorage.removeItem("loginInfo");
    setTopStatus();
    setLoginStatus("已退出登录。", "");
    loadDashboard();
  }

  function fastFillCredentials() {
    els.usernameInput.value = "admin";
    els.passwordInput.value = "123456";
    setLoginStatus("已填入演示账号，点击“登录并刷新数据”。", "");
  }

  let observer = null;
  function triggerReveal() {
    if (!observer) return;
    document.querySelectorAll(".reveal").forEach((node) => observer.observe(node));
  }

  function setupRevealObserver() {
    observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            entry.target.classList.add("visible");
            observer.unobserve(entry.target);
          }
        });
      },
      { threshold: 0.14 }
    );

    triggerReveal();
  }

  function setupHeroParallax() {
    if (!els.hero) return;

    const onScroll = () => {
      const y = Math.max(window.scrollY || 0, 0);
      const offset = Math.min(32, Math.round(y * 0.08));
      els.hero.style.backgroundPosition = `center ${offset}px`;
    };

    window.addEventListener("scroll", onScroll, { passive: true });
  }

  function init() {
    setTopStatus();
    setupRevealObserver();
    setupHeroParallax();

    els.loginForm.addEventListener("submit", login);
    els.logoutBtn.addEventListener("click", logout);
    els.quickLoginBtn.addEventListener("click", fastFillCredentials);
    els.refreshBtn.addEventListener("click", () => loadDashboard());
    els.yearSelect.addEventListener("change", () => loadDashboard());

    if (state.token) {
      setLoginStatus("已恢复登录状态。", "success");
    }

    loadDashboard();
  }

  init();
})();
