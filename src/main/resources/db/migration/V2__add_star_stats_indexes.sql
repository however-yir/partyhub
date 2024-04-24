CREATE INDEX idx_sys_branch_star_year ON sys_branch_star (sb_year);
CREATE INDEX idx_sys_branch_star_process ON sys_branch_star (process);
CREATE INDEX idx_sys_branch_star_rating ON sys_branch_star (starrating);
CREATE INDEX idx_sys_branch_star_dept ON sys_branch_star (dept_id, sb_year);
