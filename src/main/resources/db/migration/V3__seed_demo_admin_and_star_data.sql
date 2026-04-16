INSERT INTO sys_user (
    user_name,
    nick_name,
    user_type,
    password,
    status,
    del_flag,
    create_by,
    create_time,
    update_by,
    update_time,
    remark
)
SELECT
    'admin',
    '系统管理员',
    '00',
    '123456',
    '0',
    '0',
    'system',
    NOW(),
    'system',
    NOW(),
    '默认演示管理员账号'
FROM dual
WHERE NOT EXISTS (
    SELECT 1 FROM sys_user WHERE user_name = 'admin'
);

INSERT INTO sys_branch (
    partybranchname,
    establishtime,
    partynumber,
    dept_id,
    dept_name,
    branchuser_id,
    branchuser_name,
    status,
    create_by,
    create_time,
    update_by,
    update_time,
    branch_secretary_id,
    branch_secretary_name,
    remark
)
SELECT *
FROM (
    SELECT '人工智能学院第一党支部' AS partybranchname, '2018-06-01' AS establishtime, 28 AS partynumber,
           101 AS dept_id, '人工智能学院' AS dept_name, 1001 AS branchuser_id, '王强' AS branchuser_name,
           '0' AS status, 'seed' AS create_by, NOW() AS create_time, 'seed' AS update_by, NOW() AS update_time,
           2001 AS branch_secretary_id, '李敏' AS branch_secretary_name, '演示数据' AS remark
    UNION ALL
    SELECT '智能制造学院党总支', '2016-03-15', 34,
           102, '智能制造学院', 1002, '赵亮',
           '0', 'seed', NOW(), 'seed', NOW(),
           2002, '陈涛', '演示数据'
    UNION ALL
    SELECT '信息工程学院学生党支部', '2019-09-10', 22,
           103, '信息工程学院', 1003, '孙悦',
           '0', 'seed', NOW(), 'seed', NOW(),
           2003, '周娜', '演示数据'
) AS seed_rows
WHERE NOT EXISTS (
    SELECT 1 FROM sys_branch
);

INSERT INTO sys_branch_star (
    sodsacs,
    partybranchname,
    establishtime,
    partynumber,
    dept_id,
    dept_name,
    branchuser_id,
    branchuser_name,
    branch_secretary_id,
    branch_secretary_name,
    foundationitem,
    pluses,
    minuses,
    score,
    starrating,
    process,
    sb_year,
    del_flag,
    create_by,
    create_time,
    update_by,
    update_time,
    commentitems,
    remark
)
SELECT *
FROM (
    SELECT 'S2024-001' AS sodsacs, '人工智能学院第一党支部' AS partybranchname, '2018-06-01' AS establishtime,
           28 AS partynumber, 101 AS dept_id, '人工智能学院' AS dept_name, 1001 AS branchuser_id, '王强' AS branchuser_name,
           2001 AS branch_secretary_id, '李敏' AS branch_secretary_name, 82 AS foundationitem, 10 AS pluses,
           2 AS minuses, 94 AS score, '五星' AS starrating, 4 AS process, '2024' AS sb_year,
           '0' AS del_flag, 'seed' AS create_by, NOW() AS create_time, 'seed' AS update_by, NOW() AS update_time,
           4 AS commentitems, '年度评星已完成' AS remark
    UNION ALL
    SELECT 'S2024-002', '智能制造学院党总支', '2016-03-15',
           34, 102, '智能制造学院', 1002, '赵亮',
           2002, '陈涛', 78, 8,
           3, 85, '四星', 4, '2024',
           '0', 'seed', NOW(), 'seed', NOW(),
           2, '年度评星已完成'
    UNION ALL
    SELECT 'S2024-003', '信息工程学院学生党支部', '2019-09-10',
           22, 103, '信息工程学院', 1003, '孙悦',
           2003, '周娜', 74, 6,
           4, 79, '三星', 3, '2024',
           '0', 'seed', NOW(), 'seed', NOW(),
           3, '待终审'
    UNION ALL
    SELECT 'S2025-001', '人工智能学院第一党支部', '2018-06-01',
           30, 101, '人工智能学院', 1001, '王强',
           2001, '李敏', 84, 11,
           1, 96, '五星', 4, '2025',
           '0', 'seed', NOW(), 'seed', NOW(),
           2, '持续改进'
    UNION ALL
    SELECT 'S2025-002', '智能制造学院党总支', '2016-03-15',
           35, 102, '智能制造学院', 1002, '赵亮',
           2002, '陈涛', 79, 7,
           2, 86, '四星', 2, '2025',
           '0', 'seed', NOW(), 'seed', NOW(),
           2, '材料提交中'
    UNION ALL
    SELECT 'S2026-001', '信息工程学院学生党支部', '2019-09-10',
           24, 103, '信息工程学院', 1003, '孙悦',
           2003, '周娜', 80, 9,
           2, 89, '四星', 1, '2026',
           '0', 'seed', NOW(), 'seed', NOW(),
           2, '启动自评阶段'
) AS seed_rows
WHERE NOT EXISTS (
    SELECT 1 FROM sys_branch_star
);
