drop table if EXISTS dept;
create table dept(
                     deptno int(10) UNSIGNED not NULL auto_increment,
                     dname varchar(50) CHARACTER SET utf8mb4,
                     loc varchar(50) CHARACTER SET utf8mb4,
                     PRIMARY KEY (deptno) USING BTREE
) ENGINE = INNODB CHARACTER SET = utf8mb4;


insert dept VALUES(10, 'ACCOUNTING', 'NEW YORK');
insert dept VALUES(20, 'RESEARCH', 'DALLAS');
insert dept VALUES(30, 'SALES', 'CHICAGO');

drop table if EXISTS emp;
create table emp(
                    empno int(10) UNSIGNED not NULL auto_increment,
                    ename varchar(50) CHARACTER SET utf8mb4,
                    job varchar(20) CHARACTER SET utf8mb4,
                    mgr int(10) UNSIGNED NULL default null,
                    hiredate date NULL DEFAULT null,
                    sal decimal(10,2) null default null,
                    comm decimal(7,2) null default null,
                    deptno int(10) UNSIGNED NULL DEFAULT NULL,
                    PRIMARY KEY (empno) USING BTREE,
                    INDEX deptno(deptno) USING BTREE
) ENGINE = INNODB CHARACTER SET = utf8mb4;

insert emp VALUES(7369, 'SMITH', 'CLERK', 7902, '1980-12-17', 800.00, null, 20);
insert emp VALUES(7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600.00, 300.00, 30);
insert emp VALUES(7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 1250.00, 500.00, 30);
insert emp VALUES(7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 2975.00, null, 20);
insert emp VALUES(7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 1250.00, 1400.00, 30);
insert emp values(7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 2850.00, null, 30);
insert emp values(7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 2450.00, null, 10);
insert emp values(7788, 'SCOTT', 'ANALYST', 7566, '1987-04-19', 3000.00, null, 20);
insert emp values(7839, 'KING', 'PRESIDENT', null, '1981-11-17', 5000.00, null, 10);
insert emp values(7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 1500.00, 0.00, 30);
insert emp  values(7876, 'ADAMS', 'CLERK', 7788, '1987-05-23', 1100.00, null, 20);
insert emp values(7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 950.00, null, 30);
insert emp values(7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 3000.00, null, 20);