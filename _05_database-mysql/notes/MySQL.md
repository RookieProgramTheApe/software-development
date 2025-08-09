# MySQL学习笔记

## 基础知识

#### 什么是SQL语言

* SQL是用于访问和处理数据的标准的计算机语言。 
* SQL语言分类：DML（数据操作语言）、DCL（数据控制语言）、DDL（数据定义语言）。
* DML: 添加、修改、删除、查询； DCL：用户、权限、事务； DDL: 数据库、数据表、视图、索引。
* SQL语句不区分大小写，但建议关键字大写，非关键字小写。
* SQL语句必须以分号结尾。
* SQL语句中空格和换行没有限制。

#### SQL语句的两种注释

* \# 这是第一种注释（常用于单行注释）
* /* 这是第二种注释 */（常用于多行注释）

#### 创建数据库

```sql
            语法： CREATE DATABASE 数据库名称;
            
            例： CREATE DATABASE user_face_info;
```

#### 显示所有数据库

```sql
            语法： SHOW DATABASES;
```

#### 删除数据库

```sql
            语法： DROP DATABASE 数据库名称;
                
            例： DROP DATABASE user_face_info;
```







### MYSQL基本使用

#### 创建数据表

```sql
            语法： CREATE TABLE 数据表名称(
            字段名1 数据类型 [约束] [COMMENT 注释],
            字段名2 数据类型 [约束] [COMMENT 注释],
            ......
            ) [COMMENT 注释];
            
            例： CREATE TABLE student(
            id INT UNSIGNED PRIMARY KEY COMMENT '学生id',
            name VARCHAR(40) NOT NULL COMMENT '学生姓名') COMMENT '学生信息表';
            注：[]表示非必需项。
```

#### 为数据表插入数据

```sql
			语法： INSERT 数据表名 VALUES('字段1的值','字段2的值'......);
			
			例： INSERT student VALUES(1, '小张');
```

#### 显示当前数据库中的所有数据表

```sql
			语法： SHOW TABLES;
```

#### 查看数据表的表结构

```sql
			语法： DESC 数据表名;
			
			例： DESC student;
```

#### 显示数据表的建表语句

```sql
			语法： SHOW CREATE TABLE 数据表名;
			
			例： SHOW CREATE TABLE student;
```

#### 为已有数据表新增字段

```sql
			语法： ALTER TABLE 数据表名 
			ADD 字段名称 数据类型 [约束] [COMMENT '注释'],
			ADD 字段名称 数据类型 [约束] [COMMENT '注释'],
			......;
			
			例： ALTER TABLE student
				ADD home_tel CHAR(11) NOT NULL COMMENT '家庭电话';
```

#### 修改已有字段数据类型及约束

```sql
			语法： ALTER TABLE 数据表名 
			MODIFY 要修改的字段名 新的数据类型 [约束] [COMMENT '注释'],
			MODIFY 要修改的字段名 新的数据类型 [约束] [COMMENT '注释'],
			......;
			
			例： ALTER TABLE student
			MODIFY home_tel VARCHAR(11) NOT NULL COMMENT '家庭电话号码';
```

#### 修改已有字段名称

```sql
			语法： ALTER TABLE 数据表名 
			CHANGE 原来的字段名 新的字段名 数据类型 [约束] [COMMENT '注释'],
			CHANGE 原来的字段名 新的字段名 数据类型 [约束] [COMMENT '注释'],
			......;
			
			例： ALTER TABLE student
			CHANGE home_tel home_phone CHAR(11) NOT NULL COMMENT '住宅电话';
```

#### 删除已有字段

```sql
			语法： ALTER TABLE 数据表名
			DROP 要删除的字段名1,
			DROP 要删除的字段名2,
			......;
			
			例： ALTER TABLE student
			DROP home_phone;
```

#### 删除数据表

```sql
			语法： DROP TABLE 数据表名;
			
			例： DROP TABLE student;
```







### 数据库的范式及字段约束

* 数据库的范式：

  * 构造数据库必须遵循一定的规则，这种规则就是范式。
  * 关系型数据库有6种范式，一般情况下，只满足第三范式即可。

* 第一范式：原子性

  * 第一范式是数据库的基本要求，不满足这一点就不是关系数据库。

  * 数据表的每一列都是不可分割的基本数据项，同一列中不能有多个值，也不能存在重复的属性。

  * 反例：不符合第一范式（因为班级字段里的数据还能继续拆分）

    | 学号  | 姓名 |     班级     |
    | :---: | :--: | :----------: |
    | 10001 | 小天 | 高三年级一班 |

  * 正例：

    | 学号  | 姓名 | 年级 | 班级 |
    | :---: | :--: | :--: | :--: |
    | 10001 | 小天 | 高三 | 一班 |



* 第二范式：唯一性

  ​		数据表中的每条记录必须是唯一的。为了实现区分，通常要为表加上一个列用来存储唯一标识，这个唯一属性列被习惯性称作主键列。



* 第三范式：关联性

  * 每列都与主键有直接关系，不存在传递依赖。

  * 反例：不符合第三范式

    | 爸爸 | 儿子 |  女儿  | 女儿的玩具 | 女儿的衣服 |
    | :--: | :--: | :----: | :--------: | :--------: |
    | 陈华 | 陈浩 | 陈婷婷 |  海绵宝宝  |    校服    |

  * 正例：应该将女儿的玩具和衣服字段以女儿为主键新建一张数据表。

    * 第一张表：

      | 爸爸 | 儿子 |  女儿  |
      | :--: | :--: | :----: |
      | 陈华 | 陈浩 | 陈婷婷 |

    * 第二张表：

      |  女儿  | 女儿的玩具 | 女儿的衣服 |
      | :----: | :--------: | :--------: |
      | 程婷婷 |  海绵宝宝  |    校服    |

    

* 字段约束

| 约束名称 |     关键字      |          描述          |
| :------: | :-------------: | :--------------------: |
| 主键约束 | **PRIMARY KEY** |  字段值唯一且不为NULL  |
| 非空约束 |  **NOT NULL**   |    字段值不能为NULL    |
| 唯一约束 |   **UNIQUE**    | 字段值唯一且可以为NULL |
| 外键约束 | **FOREIGN KEY** |  保持关联数据的逻辑性  |

  * 主键约束（PRIMARY KEY）

    * 主键约束要求字段的值在全表中必须唯一，而且不能为NULL值。

    * 建议主键一定要使用数字类型，因为数字类型的检索速度非常快。

    * 如果主键是数字类型，那么我们应该为该列设置自动增长，关键字为  **AUTO_INCREMENT**。

      ```sql
      			例： CREATE TABLE student(
      				id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
      				name VARCHAR(40) NOT NULL);
      ```

* 非空约束（NOT NULL）

  * 非空约束要求字段的值不能为NULL值。

  * NULL值时没有值，而不是空字符串。

    ```sql
                例： CREATE TABLE student(
                                id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                                name VARCHAR(40) NOT NULL);
    ```

* 唯一约束（UNIQUE）

  * 唯一约束要求字段值如果不为NULL，那么在全表中必须唯一。

  * 例：将每个学生的电话号码字段约束设置为唯一约束

    ```sql
    			例： CREATE TABLE student(
                    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(40) NOT NULL,
                    gender ENUM('男','女') DEFAULT '男',
                    graduate BOOLEAN DEFAULT false,
                    tel VARCHAR(11)  NOT NULL UNIQUE
                 );
    ```

  * 外键约束

    ​	一般不考虑使用该约束，因为容易形成外键闭环，这样的话就会导致我们无法删除任何一张表的记录。







### 索引

​		当数据库中的记录越来越多，怎么样提高检索速度就显得重要了。而索引机制，就是为了提高数据检索速度的。

* 如何创建索引（INDEX）

  ```sql
  			语法： CREATE TABLE 数据表名(
  				......,
                    INDEX [索引名称] (字段),
                    ......
  			);
  ```

  例：我们为数据表student中的name字段添加索引，索引名称就是字段名称

  ```sql
  			例： CREATE TABLE student(
                  id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                  name VARCHAR(40) NOT NULL,
                  gender ENUM('男','女') DEFAULT '男',
                  graduate BOOLEAN DEFAULT false,
                  tel VARCHAR(11)  NOT NULL UNIQUE,
                  INDEX (name)
              );
  ```

  ​	注：如果我们填写了索引名称，那么索引就是名称，如果没有填写索引名称，那么默认就是字段名称，例如在上面的SQL语句中，INDEX(name)，就表示我们要为name字段创建索引，并且索引的名称就是name。

* 如何为已有数据表添加或删除索引

  添加索引

  ```sql
  			语法一： CREATE INDEX 索引名称 ON 数据表名(要添加索引的字段名);
  			
  			语法二： ALTER TABLE 数据表名 ADD INDEX [索引名称](要添加索引的字段名);
  ```

  查看某一数据表中索引列表

  ```sql
  			语法： SHOW INDEX FROM 数据表名;
  ```

  删除索引

  ```sql
  			语法： DROP INDEX 索引名称 ON 数据表名;
  ```

* 索引的使用原则

  * 数据量很大，而且经常被查询的数据表可以设置索引。
  * 如果数据表的写入量远远大于查询量时不适合使用索引。
  * 索引只添加在经常被用作检索条件的字段上面。
  * 不要在大字段上创建索引，例如字段长度超过50个字符。







### MySQL常用数据类型

* 数字

```sql
           	 类型                 大小           说明
           	 TINYINT			1字节			小整数
           	 SMALLINT			2字节			普通整数
           	 MEDIUMINT			3字节			普通整数
           	 INT				4字节			较大整数
           	 BIGINT				8字节			大整数
           	 FLOAT				4字节			单精度浮点数
           	 DOUBLE				8字节			双精度浮点数
           	 DECIMAL			-----		  DECIMAL(10,2)
```

注：当对数据精度要求很高时，应使用DECIMAL类型，而不是FLOAT或DOUBLE。

* 字符串类型

```sql
           	 类型                 大小           说明
           	 CHAR			 1~255字符		固定长度字符串
           	 VARCHAR		 1~65535字符		不固定长度字符串
           	 TEXT			 1~65535字符		不确定长度字符串
           	 MEDIUMTEXT		 1~1千6百万字符     不确定长度字符串
           	 LONGTEXT		 1~42亿字符		不确定长度字符串
```

* 日期类型

```sql
           	 类型                 大小           说明
           	 DATE			  	3字节			日期
           	 TIME				3字节			时间
           	 YEAR				1字节			年份
           	 DATETIME			8字节			日期时间
           	 TIMESTAMP			4字节			时间戳
```

* 枚举类型(ENUM)

```sql
           	语法： CREATE TABLE student(
                id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(40) NOT NULL,
                gender ENUM('男','女') DEFAULT '男');
```

注：枚举类型用关键字ENUM表示，枚举值之间用多个，分开，DEFAULT关键字表示该列的默认值是什么。

* 布尔类型(BOOLEAN)

  ​		实际上在MySQL中，该类型是用tinyint(1)来表示的，在SQL语句中的TRUE和FALSE分别用数字1和0代替。

```sql
			语法： CREATE TABLE student(
                id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(40) NOT NULL,
                gender ENUM('男','女') DEFAULT '男',
                graduate BOOLEAN DEFAULT false
             );
```



### 数据表的基本查询

* 普通查询

```sql
			语法： SELECT 字段名1,字段名2...... FROM 要查询的数据表名;
			
			例： SELECT id, name, tel FROM student;
			
			注： 如果要显示表中所有字段名可用*代替
			例： SELECT * FROM student;
```

* 使用例别名

  ​		通常情况下，SELECT子句中使用了表达式，那么这列的名字就默认为表达式，因此就需要对该列名重命名，也就是为该列名起一个别名。

  ```sql
  			语法 SELECT 字段名1 AS 别名1, 字段名2 AS 别名2...... FROM 要查询的数据表名;
  			
  			例： SELECT id AS student_id, name, tel FROM student;
  ```

  我们先来看这种情况是怎么发生的

  ```sql
              表结构： CREATE TABLE staff_salaries(
                                          id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                                          name VARCHAR(50) NOT NULL,
                                          salaries DECIMAL(20,5) NOT NULL,
                                          tel VARCHAR(11) NOT NULL
                                       );
  
              表数据： INSERT staff_salaries VALUES
                  (1, '程勋', 5600.00, '13954685278'),
                  (2, '谢诚', 5800.00, '18845782154');
  
              通过表达式计算出每个员工年收入：
              	SELECT id, name, salaries*12，tel FROM staff_salaries;
  
              备：此时我们就能很明显的看出原本列名为salaries变成了salaries*12了，所以就需要给该列名起别名。
  
              修改后： 
              	SELECT id, name, salaries*12 AS yearly_salaries, tel  FROM staff_salaries;
      			
    
  ```

  注： 为列名起别名是针对于结果集的，而不是修改原本数据表中的字段名称。



 * 数据分页

   ​		如果结果集中的记录很多，则可以使用LIMIT关键字限定结果集数量。

   ```sql
   			语法： SELECT ...... FROM 要查询的数据表名 LIMIT 起始值, 偏移量;
   			
   			例： CREATE TABLE staff_salaries(
                       id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(50) NOT NULL,
                       salaries DECIMAL(20,5) NOT NULL,
                       tel VARCHAR(11) NOT NULL
               	  );
               	  
   			表数据：
                       INSERT staff_salaries VALUES
                       (1, '程勋', 5600.00, '13954685278'),
                       (2, '谢诚', 5800.00, '18845782154'),
                       (3, '陈涛', 4600.00, '15325468574'),
                       (4, '王媞', 6500.00, '13524510879'),
                       (5, '田海', 5300.00, '15587451254'),
                       (6, '戴妃', 8500.00, '13541542654');
                       
   			要查询第一条到第三条的记录：
   				  SELECT * FROM staff_salaries LIMIT 0, 3;
   				  
   			要查询第四条到第六条的记录：
   				  SELECT * FROM staff_salaries LIMIT 3, 3;
   		
   		备： 要分页查询时，一般改变的是起始值，而偏移量一般都是固定不变的，注意起始值是从0开始的。
   
   ```



* 结果集排序

  ​		如果想让结果集按照某种顺序排列，就必须使用ORDER BY子句。

  ```sql
  			语法： SELECT ......FROM ...... ORDER BY 列名 [ASC | DESC];
  			备： ASC表示升序排序(默认)； DESC表示降序排序
  				
  			例：按员工工资的降序排序：
  				  SELECT * FROM staff_salaries ORDER BY salaries DESC;
  			
  ```

  ​		如果排序列是数字类型，那么就按照数字大小排序，如果是日期类型就按照日期大小排序，如果是字符串就按照字符集序号排序。

  

  

  * 多字段组合排序

    ​		如果两条数据排序字段内容相同，那么默认是按照主键的升序来排序的。

  ```sql
  			语法： SELECT ......FROM ...... ORDER BY 列名 [ASC | DESC], 列名 [ASC | DESC]......;
  			
  			例： 按员工工资的升序排列，若果工资相同，则按id值降序排序
  				  SELECT * FROM staff_salaries ORDER BY salaries, id DESC;
  				  
  			例： 按员工工资的升序排列，若果工资相同，则按id值降序排序，并限定结果集为3个
  			  SELECT * FROM staff_salaries ORDER BY salaries, id DESC LIMIT 0, 3;
  ```

  * 去除重复记录

  ```sql
  			语法： SELECT DISTINCT 字段 FROM 数据表名;
  			
  			例：CREATE TABLE employee(
                      id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(50) NOT NULL,
                      job ENUM('总经理','部门经理','员工') DEFAULT '员工'
                  );
                  
               表数据： INSERT employee VALUES
                          (1, '王涛', '总经理'),
                          (2, '丁康', '员工'),
                          (3, '何帅', '员工'),
                          (4, '田铎', '部门经理');
                          
               查询数据表中有多少个不同的职位信息：
               		  SELECT DISTINCT job FROM employee;
               
               备：
               	1. 使用DISTINCT的SELECT子句中只能查询一列数据，如果查询多列，去除重复记录就会失效。
               	2. DISTINCT关键字只能在SELECT子句中使用一次。
               	
  
  ```



* 条件查询

  ​		有时候用户只是要取出满足指定条件的记录，这类条件要用WHERE子句来实现数据的筛选。

* 使用WHERE子句时，条件执行的顺序是从左到右的，所以我们应该优先把索引条件，或者筛选掉记录最多的条件写在最左侧。

  ```sql
  			语法： SELECT ...... FROM 数据表名 WHERE 条件 [AND | OR] 条件......;
  			
  			例： 查询出id值为1的员工记录
  				  SELECT id, name, job FROM employee WHERE id = 1;
  				  
  				逻辑与：
  				  SELECT id, name, job FROM employee WHERE id = 1 AND job = '总经理';
  			  
  				逻辑或：
  			  	  SELECT id, name, job FROM employee WHERE job = '员工' OR job = '总经理';
  ```

  * WHERE子句中的条件运算会用到以下四种运算符：

    | 序号 |   运算符   |
    | :--: | :--------: |
    |  1   | 数学运算符 |
    |  2   | 比较运算符 |
    |  3   | 逻辑运算符 |
    |  4   | 按位运算符 |

  

  

  * 数学运算符

    ​	数学运算符分为+，-，*，/, %这五种。

  

  

  * 比较运算符

  | 序号 |   表达式    |    意义    |
  | :--: | :---------: | :--------: |
  |  1   |      >      |    大于    |
  |  2   |     >=      |  大于等于  |
  |  3   |      <      |    小于    |
  |  4   |     <=      |  小于等于  |
  |  5   |      =      |    等于    |
  |  6   |     !=      |   不等于   |
  |  7   |     IN      |    包含    |
  |  8   |   NOT IN    |   不包含   |
  |  9   |   IS NULL   |    为空    |
  |  10  | IS NOT NULL |   不为空   |
  |  11  | BETWEEN AND |    范围    |
  |  12  |    LIKE     |  模糊查询  |
  |  13  |   REGEXP    | 正则表达式 |

  ```sql
  			例： 查找出序号2和4两条记录中工资高于3000的人员记录
  				SELECT * FROM employee WHERE id IN(2,4) AND salaries > 3000;
  			
  			例： 查找出工资为NULL值的所有员工信息
  				SELECT * FROM employee WHERE salaries IS NULL;
  				
  			例： 查找出工资不为NULL值的所有员工信息
  				SELECT * FROM employee WHERE salaries IS NOT NULL;
  			备： 使用“字段名=NULL”或“字段名!=NULL”来判断字段值是否为NULL或不为NULL是不对的，请务必注意。
  			
  			例： 查找出工资范围在4500到6500范围的所有员工信息（BETWEEN AND方式）
  				SELECT * FROM employee WHERE salaries BETWEEN 4500 AND 6500;
  				等价于下列SQL语句：
  				SELECT * FROM employee WHERE salaries >= 4500 AND salaries <= 6500;
  			
  			例： 查找出姓名为“田”的所有员工信息
  				SELECT * FROM employee WHERE name LIKE '田%'; 
  				备：“%”表示任意字符，也就是说，只要是姓田的员工信息全查询出来。
  			
  			例： 查找出姓名最后一位以“涛”结尾的所有员工信息
  				SELECT * FROM employee WHERE name LIKE '%涛'; 
  			
  			例： 查找出姓名为2个字的所有员工记录（一个下划线表示一个占位符）
  				SELECT * FROM employee WHERE name LIKE '__'; 
  ```

  * 逻辑运算符

  | 序号 | 表达式 |   意义   |
  | :--: | :----: | :------: |
  |  1   |  AND   |  逻辑与  |
  |  2   |   OR   |  逻辑或  |
  |  3   |  NOT   |  逻辑非  |
  |  4   |  XOR   | 逻辑异或 |

  ```sql
  			例：查找出工资高于5600且职位是“员工”的所有人员信息
  				SELECT * FROM employee WHERE job = '员工' AND salaries > 5600;
  			
  			例： 查找出职位为“员工”或者为“总经理”的所有人员信息
  				SELECT * FROM employee WHERE job = '员工' OR  job = '总经理';
  			
  			例： 查找出除“员工”职位以外的所有人员信息
  				SELECT * FROM employee WHERE NOT job = '员工';
  ```

  注： XOR（逻辑异或）在MySQL中基本不会用到，所以没有举相关的例子。它表示的含义为：当任意一个操作数为NULL时,返回值为NULL，对于非NULL的操作数,如果两个的逻辑真假值相异，则返回结果为1，否则为0,就是两个不能同时成立,也不能同时不成立,只成立其中一个条件。





### 数据表的高级查询（一）

 * 聚合函数

   * 聚合函数在数据的查询分析中，应用十分广泛。集合函数可以对数据求和、求最大值、最小值、求平均值等等。
   * 聚合函数中的AVG函数和SUM函数只能用于数字类型，如果用于字符类型的统计结果为0，日期类型统计结果是转为毫秒级再统计。
   * 聚合函数中的MAX函数和MIN函数可以用于日期类型的统计。
   * COUNT函数有两种用法，一种是统计表中所有的记录，不管记录中的字段值是否为NULL，语法为：COUNT(*)；另一种是显式的指定列名，该方式会排除掉该列含NULL值的记录。

   | 序号 | 函数名称 |   意义   |
   | :--: | :------: | :------: |
   |  1   |  AVG()   | 求平均值 |
   |  2   |  SUM()   |   求和   |
   |  3   |  MAX()   | 求最大值 |
   |  4   |  MIN()   | 求最小值 |
   |  5   | COUNT()  | 求记录数 |

   

```sql
			例： 查询出公司人员的平均工资是多少（AVG函数）
				SELECT AVG(salaries) AS average_salaries FROM employee;
			
			例： 查询出公司需要支出的总工资是多少（SUM函数）
				SELECT SUM(salaries) AS total_salaries FROM employee;
			
			例： 查询出公司员工最高工资是多少（MAX函数）
				SELECT MAX(salaries) AS max_salaries FROM employee;
				
			例： 查询出公司员工最低工资是多少（MIN函数）
				SELECT MIN(salaries) AS MIN_salaries FROM employee;
				
			例： 查询出公司最早入职的员工信息
				SELECT MIN(hiredate) FROM employee;
			
			例： 查询出公司有多少名员工
				SELECT COUNT(*) FROM employee;
			
			例： 查询出公司中有多少名员工并排除工资异常的记录
				SELECT COUNT(salaries) FROM employee;
```



* 分组查询（GROUP BY）

  ​		为什么要分组？ 因为默认情况下聚合函数是对全表范围内的数据做统计。 而有时候我们的需求可能是先分组在统计，比如我们要统计每个部门的员工平均薪资是多少，这时候就应该先将各部门分组后再对各部门薪资取平均值。

  ​		GROUP BY子句的作用是通过一定的规则将一个数据集划分成若干个分组，然后针对每个分组分别进行数据汇总处理。

  ```sql
  			例： 查询出每个部门的平均薪资是多少
  				SELECT job, AVG(IFNULL(salaries, 0))  FROM employee GROUP BY job;
  				
  			例： 查询出每个部门里同一天入职的员工平均薪资是多少
  				SELECT job, AVG(IFNULL(salaries, 0)), COUNT(*)  FROM employee GROUP BY job, hiredate;
  ```

  

  **注：SELECT子句中含有GROUP BY子句时，那么SELECT子句中的内容就 必须遵守规定：SELECT子句中可以包括聚合函数，或者GROUP BY子句的分组列，其余内容均不可以出现在SELECT子句中。**

  ```sql
  			正例： SELECT job, COUNT(*) FROM employee GROUP BY job;
  			备： job字段满足规定中的第二个条件，即是GROUP BY子句的分组列，count(*)是聚合函数，也满足规定。
  			
  			反例： SELECT name, COUNT(*) FROM employee GROUP BY job;
  			备：name字段不满足规定，在实际测试中，MySQL版本为8.0.26时，该SQL语句可以执行成功，但是我们很清楚该SQL是不正确的，因为GROUP BY子句是按照字段值进行分组的，这意味着每组的记录一定不唯一，而这里的name字段只能拿到每个分组里的第一条记录。
  ```

* 对分组结果集再次做汇总计算

```sql
				例： 将员工信息按职位分组后计算出每种职位的平均薪资，并计算出公司所有员工的平均薪资和所有公司员工人数
					SELECT job, COUNT(*), AVG(salaries) FROM employee GROUP BY job WITH ROLLUP;
```

* GROUP_CONCAT函数

  ​		GROUP_CONCAT函数可以把分组查询中的某个字段拼接成一个字符串。

```sql
				例：查询出每种职位薪资高于4000的员工人数和员工姓名
					SELECT job, COUNT(*), GROUP_CONCAT(name) FROM employee WHERE salaries >= 4000 GROUP BY job;
				通过结果很明显的看到，满足条件的员工姓名被拼接在了name字段上了，这就是GROUP_CONCAT函数的作用。也就是将满足条件的记录通过字符串拼接的方式拼接后组成一条记录。
```



* Having子句

  ​		当分组时需要用到聚合函数来作为条件时，就需要用到Having子句了，需要注意的是，Having子句是依赖于GRUOP BY子句存在的。在MySQL里，如果查询条件里有聚合函数作为条件只能使用Having实现，WHERE子句不能处理带有聚合函数的查询条件，如果查询条件中没有涉及聚合函数那使用WHERE子句足以。

  ```sql
  		表结构： CREATE TABLE employee(
                  id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'id主键',
                name VARCHAR(50) NOT NULL COMMENT '员工姓名',
                  tel VARCHAR(11) UNIQUE COMMENT '员工电话',
                  job ENUM('董事长', '总经理', 'Java后端开发工程师', 'WEB开发工程师', 'Android开发工程师', '未知') DEFAULT '未知' COMMENT '职位',
                  salary DECIMAL(20, 8) NOT NULL DEFAULT 0
              );
          表数据： INSERT employee VALUES
                  (1, '王浩', '13587458752', 'WEB开发工程师', 7000),
                  (2, '田东', '18981608382', 'WEB开发工程师', 6500),
                  (3, '董蝶', '16654571330', 'WEB开发工程师', 7500),
                  (4, '李东', '18754771505', 'WEB开发工程师', 7000),
                  (5, '孟涛', '15326478580', 'WEB开发工程师', 8000),
                  (6, '白迪', '15524157563', 'Java后端开发工程师', 10000),
                  (7, '张腾', '13325452016', 'Java后端开发工程师', 12000),
                  (8, '刘强', '19875426487', 'Java后端开发工程师', 11000),
                  (9, '卤蛋', '15365612542', 'Android开发工程师', 13000),
                  (10, '陶鑫', '14658741251', 'Android开发工程师', 15000);
                  
          例： 查找出各职位平均薪资高于8000的所有员工姓名，职位和人数
          	SELECT GROUP_CONCAT(name) AS '员工姓名', job AS '职位', COUNT(*) AS '人数' FROM employee GROUP BY job HAVING AVG(salary) >= 8000;
          
          例： 查找出各职位员工工资薪资在7000及以上并且人数大于2的职位名称和员工姓名
              SELECT job AS '职位', GROUP_CONCAT(name) AS '姓名' FROM employee WHERE salary >= 7000 GROUP BY job HAVING count(*) > 2;
  ```




### 数据表的高级查询（二）

* 表连接查询
  * 从多张表中提取数据，必须制定关联的条件。如果不指定关联条件就会出现无条件连接，两张表的数据回交叉连接。产生笛卡尔积。
  * 表连接分为两种，**内连接**和**外连接**。内连接是结果集中只保留符合连接条件的记录；外连接是不管符不符合连接条件，记录都要保留在结果集中。



   * 内连接

     * 内连接是最常见的一种表连接，用于查询多张关系表符合连接条件的记录。

     ```sql
     			语法： SELECT ...... FROM 数据表1
     				[INNER] JOIN 数据表2 ON 连接条件
     				[INNER] JOIN 数据表3 ON 连接条件
     				......;
     				备：[]为非必填项，在实际开发中，我们常常省略INNER关键字。
     				
     			其它内连接语法形式(使用WHERE子句代替ON子句)：
     				SELECT ...... FROM 数据表1 JOIN 数据表2 WHERE 连接条件;
     				
     			其它内连接语法形式(省略JOIN关键字用逗号代替)：
     				SELECT ...... FROM 数据表1, 数据表2 ON 连接条件;	
     ```

     * 内连接示例：

     ```sql
     		新建一张员工考勤表：
     			表结构： CREATE TABLE attendance(
                     id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'id主键',
                     e_id INT UNSIGNED NOT NULL COMMENT '员工id',
                     sign_in_time TIMESTAMP COMMENT '打卡时间'
                 );
     
                 表数据： INSERT attendance VALUES
                         (1, 1,'2021-12-01 17:24:13'),
                         (2, 1,'2021-12-22 13:17:23'),
                         (3, 1,'2021-12-15 15:52:03'),
                         (4, 2,'2021-12-01 12:38:05'),
                         (5, 2,'2021-12-12 16:25:59'),
                         (6, 2,'2021-12-25 13:37:23'),
                         (7, 3,'2021-12-05 12:27:02');
                         
                 使用内连接将两张表连接起来，连接条件为员工信息表里的id字段与考勤表里的e_id字段相等：
                 	SELECT name, attendance.sign_in_time FROM employee INNER JOIN attendance ON employee.id = attendance.e_id;
                 备：在实际开发中，常常会给一个表起别名，那样的话整体的SQL语句书写量就会少很多了。
                 	
                 将上面的SQL语句中的表名用别名代替后：
                 SELECT name, a.sign_in_time FROM employee e INNER JOIN attendance a ON e.id = a.e_id;（employee后面的e就是我们起的别名，要为哪张表起别名，就在表名加空格后起别名就行）
                 备： 需要注意的是，如果两张表里有同名的字段，请显式的指定出来
                 例：我们要显示employee里的id、name、及attendance里的sign_in_time字段：
                 反例： SELECT id, name, a.sign_in_time FROM employee e INNER JOIN attendance a ON e.id = a.e_id;
                 正例：	SELECT e.id, name, a.sign_in_time FROM employee e INNER JOIN attendance a ON e.id = a.e_id;(显示的指定id我们要显示的id字段是employee表里的)
                
     ```

       * 练习
          查找和“王浩”相同职位的员工姓名有哪些

     ```sql
     		子查询写法： SELECT name FROM employee WHERE job = 
     				(SELECT job FROM employee WHERE name = '王浩') AND name != '王浩';  
     
     		解析：子查询就是将查询语句作为条件嵌套在另一个查询语句中，将要作为子查询的查询语句用()包裹起来即可，在上面的SQL语句中，我们先查询出“王浩”的职位作为筛选条件后，就能得到与王浩相同职位的其它员工信息，然后在排除掉“王浩”本人的记录。
     		缺点： 因为筛选条件是子查询，所以当表中记录很多时，效率可能会不好。
     		
     		表连接写法： SELECT e2.name FROM employee e1 
                         JOIN employee e2 ON e1.job = e2.job
                         WHERE e1.name = '王浩' AND e2.name != '王浩';
             解析： 表连接查询是可以与本表连接的，这里我们用职位作为连接条件后可以得到针对于相同职位的笛卡尔积结果集，所以我们在筛选条件中再次指定是第一张表姓名为“王浩”的员工，这样就能得到和“王浩”职位相同的所有人员信息，最后再排除掉“王浩”本人的记录。
     ```

       * 练习
          查找出薪资高于公司平均薪资的员工姓名

     ```sql
     		SELECT name FROM employee JOIN (SELECT AVG(salary) AS avg_salary FROM employee) avg_employee ON employee.salary > avg_employee.avg_salary; 
     		
     		备： 表连接不仅可以连接实际存在的表，也可以连接利用SELECT子句查询出来的结果集，也称为临时表，使用方式是（SELECT ...... FROM 表名） 别名， 然后就可以用别名来指代临时表了。
     ```




* 外连接

  * 除了显示符合连接条件的记录之外，结果集中还会保留不符合条件的记录。

  * 连接两张表时，对于不符合连接条件的记录需要保留的情况就只能使用外连接了。例如现在有两张表，一张为员工信息表，员工信息表里姓名为“王浩”的记录部门编号缺失（为NULL值），一张为部门信息表，两张表的连接条件是员工信息表的部门编号与部门信息表的id相同，如果这时用内连接，那么姓名为“王浩”的记录就会被筛选掉，这显然是不合理的。

  ```sql
  			例： 查询出所有员工考勤情况，没考勤的员工考勤记录用NULL值代替。
  				SELECT e.name, a.sign_in_time FROM employee e LEFT JOIN attendance a ON e.id = a.e_id;
  ```

  * 左连接与右连接

    ​		左外连接就是保留左表所有的记录，与右表做连接。如果右表有符合条件的记录就与左表连接。如果右表没有符合条件的记录，就用NULL与左表连接。右外连接则相反，总之，我们要保留哪张表，就用指向该表方向的外连接即可。

  

  * UNION

    ​		UNION关键字可以将多个查询语句的结果集进行合并（必须保证多个结果集的字段是匹配的，否则不能合并）

    ```sql
    		例： 将两个查询语句的结果集进行合并
            	(SELECT name FROM employee WHERE name = '王浩') 
            		UNION 
            	(SELECT name FROM employee WHERE name = '田东');
            备：在外连接中，如果两个表中的记录无论是否匹配都要查询出来，那么就只能通过UNION关键字合并由左外连接和右外连接组成的SQL语句。
            
            例： 查询出所有员工的考勤信息（包括无法匹配的考勤信息）
            (SELECT * FROM employee e LEFT JOIN attendance a ON e.id = a.e_id)
            	UNION
            (SELECT * FROM employee e RIGHT JOIN attendance a ON e.id = a.e_id);
    ```

  * 外连接注意事项

    ​		内连接只保留符合条件的记录，所以查询条件写在ON子句和WHERE子句中的效果是相同的。但是在外连接中，条件写在WHERE子句中，不符合条件的记录是会被过滤掉的，而不是保留下来。

    ```sql
    		例： 查找出员工姓名“王浩”的所有考勤记录
    			SELECT * FROM employee e LEFT JOIN attendance a ON e.id = a.e_id AND e.name = '王浩';	
    		备： 我们用左外连接查询时，对于姓名字段不是“王浩”的记录也保留下来了，接下来我们尝试将条件放在WHERE子句中。
    		
    		用WHERE子句后：
    			SELECT * FROM employee e LEFT JOIN attendance a ON e.id = a.e_id WHERE e.name = '王浩';
    		备： 运行后我们可以看见，对于不满足WHERE子句条件的记录已经被过滤掉了。
    ```

  

  

  * 子查询

    * 子查询就是在查询语句中嵌套查询的语句。
    * 子查询可以写在三个地方：WHERE子句、FROM子句、SELECT子句，但是只有FROM子句子查询是最可取的。

    

    * WHERE子查询

      ​	这种子查询最简单，最容易理解，但是确是效率很低的子查询，本质是将子查询的结果作为另一个查询语句的筛选条件，这也意味着表中有多少条记录就会重复执行多少次，所以不推荐使用该子查询。

      ```sql
      	例： 查找出所有与“王浩”相同职位的员工信息
      		SELECT * FROM employee WHERE job = (SELECT job FROM employee WHERE name = '王浩') AND name != '王浩';
      ```

    * FROM子查询

      ​	这种子查询只会执行一次，所以查询效率很高，推荐使用该子查询。

      ```sql
      	例： 查找出所有与“王浩”相同职位的员工信息
      		SELECT * FROM employee e JOIN (SELECT job FROM employee WHERE name = '王浩') t ON e.job = t.job AND e.name != '王浩';
      ```

    * SELECT子查询

      ​	这种子查询会在每次查询时执行一次，所以和WHERE子查询一样，是效率很低的子查询，都不推荐使用。

      ```sql
      	例： 查找出所有员工的姓名及第一次打卡的打卡记录
      		SELECT name, (SELECT sign_in_time FROM attendance WHERE e_id = e.id LIMIT 0, 1) FROM employee e;
      ```

    

    * 单行子查询和多行子查询

      * 单行子查询的结果集只有一条记录，多行子查询结果集有多行记录。
      * 多行子查询只能出现在WHERE子句和FROM子句中。

      

      

      * WHERE子句中的多行子查询

        ​	WHERE子句中，可以使用IN、ALL、ANY、EXISTS关键字来处理多行表达式结果集的条件判断。

        ​	IN关键字类似逻辑表达式中的逻辑或，只要等于多行子查询的结果集中任何一个即可。

        ```sql
        	例： 查找出"王浩"和"白迪"的所有同事信息
        		WHERE多行子查询写法:
        			SELECT name, job FROM employee WHERE job IN (SELECT job FROM employee WHERE name IN('王浩', '白迪')) AND name NOT IN ('王浩', '白迪');
        	
        		FROM多行子查询写法：
        			SELECT name, e.job FROM employee e JOIN (SELECT job FROM employee WHERE name IN ('王浩', '白迪')) t ON e.job = t.job AND e.name NOT IN ('王浩', '白迪');
        		
        		
        		
        ```

        ​	ALL关键字表示得同时满足所有多行子查询结果集

        ```sql
        	例： 查找出工资比"王浩"和"白迪"都高的员工信息
        		SELECT * FROM employee WHERE salary > ALL (SELECT salary FROM employee WHERE name IN ('王浩', '白迪'));
        ```

        ​	ANY关键字表示只要满足任意一行子查询结果集

        ```sql
        	例： 查找出工资比"王浩"或者比"白迪"高的员工信息
        		SELECT * FROM employee WHERE salary > ANY (SELECT salary FROM employee WHERE name IN ('王浩', '白迪'));
        ```

        





### 数据表的数据添加

​		INSERT语句用于向数据库表写入记录，可以是一条记录，也可以是多条记录。

```sql
			语法： 
				单条记录添加：
					INSERT [IGNORE] [INTO] 数据表名(字段1, 字段2, ......) VALUES(值1, 值2, ......);
				多条记录添加：
					INSERT [INTO] 数据表名(字段1, 字段2,......) VALUES(值1, 值2, ......), (值1, 值2, ......)......;
					
			例： 在员工信息表中新增一条记录
					INSERT INTO employee(name, job, salary) VALUES('张', 'Java后端开发工程师', 5600.00);
			备： 为了INSERT语句执行效率达到最高，请务必显式的告诉数据库我们要为哪些字段写入记录，而不是让MySQL通过词法分析后去数据库里帮我们查询表结构再补全字段。
			
			添加数据时嵌套子查询：
				INSERT INTO employee(name, job, salary) VALUES('李', (SELECT id FROM attendance WHERE id = 1), 6300.00);
			备： 只能是单行子查询且只能是一个字段，不然会不匹配，子查询不能查询当前添加数据的目标表。
			反例： INSERT INTO employee(name, job, salary) VALUES('李', (SELECT id FROM employee WHERE id = 1), 6300.00);
			
			IGNORE关键字：
				添加数据时为数据表添加该关键字后，会自动忽略掉冲突的数据，不会影响其他不冲突数据的添加。
			例： INSERT IGNORE INTO employee(id, name, tel, job, salary) VALUES(1, '肖锋', '13525465549', '5', 7800),(2, '肖锋', '13525465549', '5', 7800);
			
			有时我们可能需要的并不是自动忽略冲突的数据，而是覆盖掉它，那么可以使用另一种语句完成。
			语法： REPLACE INTO ......
			该语句在新增时如果发现已有相同的主键值记录存在，则先删除掉该记录，然后添加新数据，否则直接添加新数据。
```



### 数据表的数据更改

​		UPDATE语句用于更改数据库表中的记录。

```sql
			语法： UPDATE [IGNORE] 数据表名 SET 字段1 = 值1, 字段2 = 值2, ......
				[WHERE 条件 ......] 指定要更改的记录筛选条件
				[ORDER BY ......] 按指定规则排序
				[LIMIT ......] 按指定的限定值执行更改
				
			例： 更改“王浩”的职位为“Java后端开发工程师”
					UPDATE employee set job = 'JAVA后端开发工程师' WHERE name = '王浩';
				
				所有员工的id值加1
					反例： UPDATE employee SET id = id + 1;
					解析： 因为id字段为主键字段，不允许重复。id字段一般都是自增的数字类型，所以当第一条记录的id增加后，势必要与第二条记录的id值冲突。
					正例： 使用ORDER BY DESC （这样排序后id字段就不会产生冲突的情况了）
						  UPDATE employee SET id = id + 1 ORDER BY id DESC;
			
			例： 把公司员工工资排名前三位的员工工资降低500元
					UPDATE employee set salary = salary - 100 ORDER BY salary DESC LIMIT 3;（注意，这里的LIMIT不能指定起始值，起始值固定为0，只能指定偏移量）
					
			例： 将薪资低于公司平均薪资的员工薪资增加150元（连接临时表）
					UPDATE employee e JOIN (SELECT AVG(IFNULL(salary, 0)) AS avg FROM employee) t ON e.salary < t.avg SET salary = IFNULL(salary, 0) + 150;
			备： 在UPDATE语句中，我们可以使用表连接方式指定要更新的记录值是哪些。例如在上面的例子中，我们先通过临时表的方式查询出公司的平均薪资，然后在连接条件中指定员工薪资低于公司平均薪资的员工记录更新薪资字段。
			
			例： 将“王浩”记录中的name字段更改为“王小浩”, 并将该员工的所有考勤记录往前一天(表连接并同时更新多张表数据)
					UPDATE employee e JOIN attendance a SET e.name = '王小浩', a.sign_in_time = DATE_SUB(a.sign_in_time, INTERVAL 1 day) WHERE e.id = a.e_id AND e.name = '王浩';
			备： 不仅可以使用内连接，也可以根据具体业务需求使用外连接。
					·

```



### 数据表的数据删除

​		DELETE语句用于删除数据库表中的记录。

```sql
			语法： DELETE [IGNORE] FROM 数据表名
			[WHERE 条件 ......] 指定要删除的记录筛选条件
			[ORDER BY ......] 按指定规则排序
			[LIMIT ......] 按指定的限定值执行删除
			
			例： 删除员工信息表中姓名为“李东”的员工信息
					DELETE FROM employee WHERE name = '李东';
					
			例： 删除员工信息表中薪资低于公司平均薪资的员工信息
					DELETE e FROM employee e JOIN (SELECT AVG(salary) avg_salary FROM employee) t WHERE e.salary < t.avg_salary;
			解析： 对于公司平均薪资我们可以先用子查询得到，再当作临时表与employee表连接，将连接条件设置后便能锁定出要删除的员工信息时哪些（因为我们连接了另一张表，所以需要显示的告诉MySQL要删除的是哪张表里的记录，所以就有了“DELETE e FROM employee e”， 其中e表示我们要删除的是employee表中满足条件的记录，如果连接的是两张不同的表，那么也可以通过指定多张表来达到一条DELETE语句同时删除多张表的记录）。
			
			例： 将姓名为“王小浩”的员工信息与考勤信息记录删除
					DELETE e, a FROM employee e JOIN attendance a WHERE e.id = a.e_id AND e.name = '王小浩';
```



快速删除表中所有记录

```sql
			语法： TRUNCATE TABLE 要清空的数据表名;
			
			备：如果使用的是DELETE FROM 数据表名,也能达到同样的效果，但是使用TRUNCATE语句是在事务机制之外删除记录，而且使用该语句清空记录后，自动递增的主键值会重置，而DELETE语句不会。
```



### MySQL常用函数

* IFNULL()

  * 当要计算的某个字段的值为NULL值时，我们就用一个值去代替。

  ```sql
  			语法： IFNULL(字段名, 代替的值)
  			
  			例：计算出每个员工的年收入
  			表结构： CREATE TABLE employee(
                          id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(50) NOT NULL,
                          salaries DECIMAL(15,6),
                          job ENUM('总经理','部门经理','员工') DEFAULT '员工',
                          hiredate DATETIME
                		 );
                		 
                表数据： INSERT employee (id, name, salaries, job) VALUES
                          (1, '王涛', NULL, '总经理'),
                          (2, '丁康', 5600.0034, '员工'),
                          (3, '何帅', 6500, '员工'),
                          (4, '田铎', 4500, '部门经理');
                          
                通过IFNULL将可能出现NULL值的字段处理，如果为NULL值，就用0代替。
                SQL语句如下：
                	SELECT id, name, IFNULL(salaries,0)*12, job, hiredate FROM employee;
  ```

  * 为什么要这样做呢？那是因为如果该字段值为NULL值，那么在该值的基础上做的所有运算都为NULL。如下所示：

  ```sql
  			NULL值运算测试：
  				SELECT 1 + NULL AS 'NULL值加法运算';
  				SELECT 1 - NULL AS 'NULL值减法运算';
  				SELECT 1 * NULL AS 'NULL值乘法运算';
  				SELECT 1 / NULL AS 'NULL值除法运算';
  				SELECT 1 % NULL AS 'NULL值取模运算';
               所有测试结果都为NULL，这在有些情况下可能并不符合我们的要求，所以可以考虑用IFNULL函数处理该情况。
  ```





* NOW()、CURDATE()、CURTIME()、DATEDIFF()......

  ```sql
  			1. NOW()函数在MySQL表示取到当前日期和时间，格式为：yyyy-MM-dd hh:mm:ss。
  			
  			2. CURDATE()函数在MySQL表示取到当前日期，格式为yyyy-MM-dd。
  			
  			3. CURTIME()函数在MySQL表示取到当前时间，格式为：hh:mm:ss。
  			
  			4. DATEDIFF函数表示计算两个日期之间相隔了多少天。
  				语法： DATEDIFF(时间1, 时间2)
  				例： SELECT DATEDIFF(NOW(), '2021-12-10 00:15:03');
  			
  			5. 日期格式化函数：
  				DATE_FORMAT()函数用于格式化日期，返回用户想要的日期格式。
                      语法： DATE_FORMAT(日期，表达式);
                      表达式取值：
                          %Y 年份			   %m 月份				     日期
                          %w 星期（数字）		%W 星期（名称）			%j 本年第几天
                          %U 本年第几周		 %H 小时（24）			  %h 小时（12）
                          %i 分钟			   %s 秒					  %r 时间（12）
                          %T时间（24）
                      例： 查询1992年上半年入职的员工有哪些
                              SELECT name FROM employee WHERE DATE_FORMAT(hiredate,'%Y') = 1992 AND DATE_FORMAT(hiredate,'%m')<= 6;
  			
  				注： MySQL数据库中，两个日期不能直接加减，日期也不能与数字加减，语法没有错，但执行的结果是错误的，这是因为MySQL会将日期类型的数据转为数字类型后再与数字类型数据加减。
  			
  			6. DATE_ADD()函数可以实现日期的偏移计算，类似数字类型的加法运算，如果想计算之前的日期，则可以加上一个负的偏移量，也可以使用DATE_SUB()函数。
  				语法： DATE_ADD(日期, INTERVAL 偏移量 时间单位);
  				时间单位取值常见的是：SECOND、MINUTE、HOUR、DAY、WEEK、MONTH、YEAR
  				
  				例： 下个月第3天是几月几日
  					SELECT DATE_FORMAT(DATE_ADD(DATE_ADD(NOW(), INTERVAL 1 MONTH), INTERVAL 3 day), '%Y年%m月%d日');
  				备： 这里同时使用了多个函数，并且函数之间是可以嵌套使用的。
  ```



* 常用字符函数

  | 函数名称  |      功能      |             用例             |
  | :-------: | :------------: | :--------------------------: |
  |   LOWER   | 转换为小写字符 |         LOWER(MYSQL)         |
  |   UPPER   | 转换为大写字符 |         UPPER(mysql)         |
  |  LENGTH   |  返回字符长度  |        LENGTH(mysql)         |
  |  CONCAT   |   连接字符串   |     CONCAT('￥', salary)     |
  |   INSTR   | 字符出现的位置 |     INSTR('MySQL', 'y')      |
  |  INSERT   | 插入/替换字符  |  INSERT('王浩', 2, 0, '小')  |
  |  REPLACE  |    替换字符    | REPLACE('mySQL', 'my', 'No') |
  |  SUBSTR   |   截取字符串   |    SUBSTR('abcde', 1, 3)     |
  | SUBSTRING |   截取字符串   |   SUBSTRING('abcde', 2, 2)   |
  |   LPAD    |  左侧填充字符  |   LPAD('123456', 10, '#')    |
  |   RPAD    |  右侧填充字符  |   RPAD('123456', 10, '#')    |
  |   TRIM    |  去除收尾空格  |        TRIM(' trim ')        |

  ```sql
  			例：
  				将'LOSER'转为小写		SELECT LOWER('LOSER');
  				将'loser'转为大写		SELECT UPPER('loser');
  				统计'mysql'为多少个字符   SELECT LENGTH('mysql');
  				连接'My'、'SQL'、'DataBase'  SELECT CONCAT('My', 'SQL', 'DataBase');
  				查找's'在字符串中第一次出现的位置  SELECT INSTR('mysqls', 's');
  				将'  你好  '首尾空格去掉   SELECT TRIM('  你好  ');
  				
  			INSERT()函数： 
  				参数说明： INSERT(要处理的字符串, 从哪里开始替换或插入, 该值为0表示为插入大于0则表示为替换, 要替换或插入的字符串);
  				例： 新增字符'Data'在字符'MySQL'的前面
                  		SELECT INSERT('MySQL', 1, 0, 'Data');
                  	替换'MySQL'中的'My'为'No'
                  		SELECT INSERT('MySQL', 1, 2, 'No');
                  		
              REPLACE()函数：
              	参数说明： REPLACE(要处理的字符串, 要替换的字符串, 新的字符串);
  				例： 替换'MySQL'中的'My'为'No'
  						SELECT REPLACE('MySQL', 'My', 'No');
  			
  			SUBSTRING()函数：
  				参数说明： SUBSTRING(要处理的字符串, 从哪里开始截取， 截取多少个字符);
  				例： 截取'MySQL'中的'SQ'
  						SELECT SUBSTRING('MySQL', 3, 2);
  						
  			LPAD()函数与RPAD()函数：
  				参数说明：LPAD(要处理的字符串, 填充后的总字符长度, 填充字符串);
  				例： 将'abcdefg'前四位用'*'号填充
  						SELECT LPAD(SUBSTRING('abcdefg', 5, 3), 7, '*');
  				例： 将'15599227075'后四位用'*'填充
  						SELECT RPAD(SUBSTRING('15599227075', 1,7), 11, '*');
  					
  						
  				
  ```

  

* FLOOR()、CEIL()、ROUND()

  ```sql
  			FLOOR(): 表示对数据向下取整，也就是不管小数点后的小数是多少，都舍去小数部分
  			例： SELECT FLOOR(3.9);
  			
  			CEIL(): 表示对数据向上去整，也就是不管小数点后的小数是多少，都向前进1。
  			例： SELECT CEIL(4.1);
  			
  			ROUND(): 表示对数据进行四舍五入。 
  			例： SELECT ROUND(4.4); SELECT ROUND(4.5);
  			
  		备： 如果我们要精确到小数点几位时，那么可能先乘以多少再除以多少实现。 例SELECT ROUND(3.1415 * 1000) / 1000; 表示精确到小数点后3位。
  ```



### 数据库的事务机制

​		MySQL的事务机制是在5.0版本后才有的。事务日志分为redo log和undo log两种。

​        (1)、对于事务日志中未正常提交的事务，会记录到undo log中，因为事务未正确执行完，因此必须回滚，从而保证数据一致性。

​        (2)、对于事务日志中已正常提交但未同步到持久化存储上时，会记录到redo log中，因此MySQL会重新执行一遍事务，然后让数据存储到磁盘上，从而保证数据一致性。



**事务机制（Transaction）**

​	[RDBMS](https://baike.baidu.com/item/%E5%85%B3%E7%B3%BB%E6%95%B0%E6%8D%AE%E5%BA%93%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F/11032386?fromtitle=RDBMS&fromid=1048260&fr=aladdin) = SQL语句 + 事务 ([ACID](https://baike.baidu.com/item/acid/10738?fr=aladdin))

​	在事务机制下，将一个或者多个SQL语句当作一个整体，要么全部执行成功，要么全都执行失败。 

​	事务的ACID属性：原子性、一致性、隔离性、持久性。

​			原子性：一个事务中的所有操作要么全部完成，要么全部失败。事务执行后，不允许提留在中间某个状态。

​			一致性：不管在任何给定的时间、并发事务有多少，事务必须保证运行结果的一致性。

​			隔离性： 每个事务不受其他并发事务的影响，如同在给定的时间内，该事务是数据库唯一运行的事务。

​			持久性： 事务一旦提交，结果便是永久性的。即便发生宕机，仍然可以依靠事务日志完成数据的持久化。



**管理事务**

​		默认情况下，MySQL执行每条SQL语句都会自动开启和提交事务。

​		为了让多条SQL语句纳入到一个事务之下，可以手动管理事务。

```sql
			查询数据库的事务隔离级别(MySQL5.7.5+，旧版本变量为：tx_isolation)：
				SELECT @@transaction_isolation;
			开启事务：
				START TRANSACTION;
			提交事务：
				COMMIT;
			回滚事务：
				ROLLBACK；
```

**事务隔离级别**

| 序号 | 隔离级别         | 功能           |
| ---- | ---------------- | -------------- |
| 1    | read uncommitted | 读取未提交数据 |
| 2    | read committed   | 读取已提交数据 |
| 3    | repeatable read  | 可重复读       |
| 4    | serializable     | 串行化         |

1. **读未提交（Read Uncommitted）**：
   - 在这个级别，一个事务可以读取到另一个事务尚未提交的数据变更。
   - 这种隔离级别可能导致脏读（Dirty Read），即读取到可能被回滚的数据。
2. **读已提交（Read Committed）**：
   - 在这个级别，一个事务只能读取到已经提交的数据。
   - 事务开始时会获取一个快照，但在每次读取时都会获取最新的已提交数据。
   - 这种隔离级别可以避免脏读，但可能会出现不可重复读（Non-Repeatable Read）的问题，即在同一个事务中多次读取同一数据时，结果可能不同。
3. **可重复读（Repeatable Read）**：
   - 这是MySQL的默认事务隔离级别。
   - 在这个级别，一个事务在开始后就会获取一个数据的快照，并在整个事务过程中都使用这个快照进行读取。
   - 这样可以确保在同一事务中多次读取同一数据时，结果始终相同，从而避免了不可重复读的问题。
   - 但是，可重复读级别无法阻止幻读（Phantom Read）的发生，即在同一个事务中，两次执行相同的查询可能会返回不同的行数，因为其他事务可能在期间插入或删除了满足查询条件的行。
4. **串行化（Serializable）**：
   - 这是最高级别的事务隔离。
   - 在这个级别，事务之间完全串行化执行，就像单线程操作一样。
   - 这种隔离级别可以防止所有并发问题，包括脏读、不可重复读和幻读，但是会极大地降低并发性能，因为事务需要等待其他事务完成才能开始。



**更改会话的事务隔离级别**

​	例如设计一个抢票软件，A与B同时开始抢票，为了解决并发场景下用户购票体验，防止频繁的购票失败，可以将当前会话的事务隔离进行一定调整，以满足业务需求，本例中，可将当前会话的事务隔离级别设置为读为提交，这样当前会话就能在当前事务执行过程中，读取到其它事务为提交的数据了

```sql
# 设置当前会话的事务隔离级别
SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
```



**事务相关常用命令**

```sql
# 查看详细事务信息（包含进程ID）
SELECT 
    trx.trx_id,
    trx.trx_state,
    trx.trx_started,
    trx.trx_query,
    pl.id AS process_id,
    pl.user,
    pl.host,
    pl.db,
    pl.command,
    pl.time,
    pl.state,
    pl.info
FROM information_schema.INNODB_TRX trx
JOIN information_schema.PROCESSLIST pl
    ON trx.trx_mysql_thread_id = pl.id;
    
# 中止事务
kill process_id;
```



## 进阶知识

MySQL逻辑架构图

![](image/mysql.png)



### MySQL的日志文件

​	MySQL从物理结构上可以分为 **日志文件** 和 **数据及索引文件**， MySQL通过日志记录了数据库操作信息和错误信息

​	**常见日志文件**

- 错误日志error.log
- 二进制日志mysql-bin
- 通用查询日志general_query.log
- 慢查询日志slow_query_log.log
- 重做日志Redolog
- 中继日志relaylog
- Undo log

如何查看MySQL日志信息

```mysql
SHOW VARIABLES LIKE 'log_%';
```



#### 错误日志

- 默认开启，记录运行过程中 **所有严重的错误信息**， 每次启动和关闭的详细信息
- 通过log_error和log_warnings配置
  - log_error: 日志文件存储位置
  - log-warnings: 配置警告信息级别，0表示不记录告警日志，大于等于1表示记录告警日志

**配置示例**

```mysql

```



#### 二进制日志

- 默认关闭，需通过配置开启，记录数据库所有的DDL，DML语句，不包括DQL语句
- 主要用于实现主从复制、数据备份、数据恢复
- 通过log-bin配置

**配置示例**

```mysql

```





#### 通过查询日志

-  默认关闭，记录用户的所有操作，包含增删改查等详细信息。不建议开启
- 通过general_log和general_log_file配置
  - general_log: 日志开关
  - general_log_file: 日志文件存储位置

**配置示例**

```mysql

```





#### 慢查询日志

- 默认关闭，记录SQL执行时间超过设定阈值的所有查询
- 通过slow_query_log、long_query_time和slow_query_log_file配置
  - slow_query_log: 慢查询日志开关
  - long_query_time: 慢查询阈值
  - slow_query_log_file: 日志文件存储位置

**配置示例**

```mysql

```





### MySQL数据文件

#### 存放目录查询

```mysql
SHOW VARIABLES LIKE '%datadir%';
```



#### 目录文件说明

- **ibdata文件** 
  - 使用 **系统表空间** 存储表数据和索引信息，那么数据存储在一个或多个ibdata文件中
- **InnoDB存储引l擎的数据文件**
  - 表名.frm文件：主要存放与表相关的元数据信息，包括：**表结构的定义信息**
  - 表名.ibd文件：一张表一个ibd文件，存储表数据和索引l信息
- **MyISAM存储引I擎的数据文件**
  - 表名.frm文件：主要存放与表相关的元数据信息，包括：**表结构的定义信息**
  - 表名.myd文件：主要存放数据
  - 表名.myi文件：主要存放索引l





### 案例：分析一条SQL语句的完整执行流程



待分析SQL语句如下

```sql
SELECT u_id, name FROM user WHERE u_id = 9;
```

大体来说，MySQL可以分为 **Server层** 和 **存储引擎层** 两部分

1. **Server层**
   - 包括：连接器、查询缓存、分析器、优化器、执行器等
   - 涵盖MySQL的大多数核心服务功能
   - 所有的内置函数（如日期、时间、数学和加密函数等），所有跨存储引擎的功能都在这一层实现
     - 比如：存储过程、触发器、视图等
2. **存储引擎层**
   - 负责数据的存储和提取
   - 可插拔式存储引擎：InnoDB、MylSAM、Memory等
   - 最常用存储引擎是InnoDB
   - 从MySQL5.5版本开始，默认存储引擎是InnoDB

![](image/excute.png)

#### 执行流程

##### ① 连接数据库

​	首先建立与MySQL服务器的连接

```sql
-- 连接命令 指定ip,端口，连接时使用的用户名
mysql -h127.0.0.1 -P3306 -uroot -p
```

​	连接完成后，如果你没有后续的动作，这个连接就处于空闲状态。客户端如果太长时间没请求，连接器就会自动断开与它的连接。这个时间是由参数wait_timeout控制的，默认值是28800秒，也就是8小时

```sql
-- 查看wait_timeout参数值
SHOW VARIABLES LIKE 'wait_timeout';
```

​	可通过“SHOW PROCESSLIST”来查看当前MySQL服务器所持有的所有客户端连接信息，可通过对应信息查看连接的状态信息等

```sql
SHOW PROCESSLIST;
```

**执行截图**

![](image/process_list.png)

| 列名        | 说明                                                         |
| ----------- | ------------------------------------------------------------ |
| **Id**      | 连接mysql 服务器线程的唯一标识，可以通过kill来终止此线程的链接 |
| **User**    | 当前线程链接数据库的用户                                     |
| **Host**    | 显示这个语句是从哪个ip 的哪个端口上发出的。可用来追踪出问题语句的用户 |
| **db**      | 线程链接的数据库，如果没有则为null                           |
| **Command** | 显示当前连接的执行的命令，一般就是休眠或空闲（sleep），查询（query），连接（connect） |
| **Time**    | 线程处在当前状态的时间，单位是秒                             |
| **State**   | 显示使用当前连接的sql语句的状态，很重要的列，后续会有所有的状态的描述，请注意，state只是语句执行中的某一个状态，一个 sql语句，已查询为例，可能需要经过copying to tmp table，Sorting result，Sending data等状态才可以完成 |
| **Info**    | 线程执行的sql语句，如果没有语句执行则为null。这个语句可以使客户端发来的执行语句也可以是内部执行的语句 |



##### ② 查询缓存

​	这里需要分两种情况，在MySQL8.0，已经废弃了查询缓存功能，以下命令是在常用的5.7版本中操作

​	查询缓存默认是关闭的状态，也不建议使用该功能

查询是否开启查询缓存及缓存命中次数

```mysql
-- 查看是否开启缓存功能
SHOW VARIABLES LIKE 'query_cache_type';

-- 查看缓存命中次数
SHOW status LIKE 'qcache_hits';
```

**如何开启缓存**

​	在MySQL配置文件中，修改query_cache_type参数，值为“0或OFF”表示禁止使用缓存，值为“1或ON”将启用缓存，但以“SELECT SQL_NO_CACHE”开头的语句除外；值为“2或DEMAND”时，只缓存以“SELECT SQL_CACHE”开头的语句

**清空查询缓存**

```sql
-- 清理查询缓存内存碎片
FLUSH QUERY CACHE;

-- 从查询缓存中移除所有查询
RESET QUERY CACHE;

-- 关闭所有打开的表，同时该操作将会清空查询缓存中的内容
FLUSH TABLES
```



为什么不建议使用MySQL**查询缓存**

- **成本高：** 查询缓存的失效非常频繁，只要对一个表有更新，那么这个表对应的所有查询缓存都会被清空
- **命中率：** 对于数据更新压力大的数据库来说，查询缓存的命中率会非常低
- **功能不如其他专业的缓存工具：** redis、memcache



##### ③ 分析SQL

​	如果查询缓存没有命中，接下来就需要进入分析器的环节了，客户端程序发送过来的只是一个字符串而已，所有MySQL需要识别出里面的字符串分别是什么，代表什么。判断请求的语法是否正确，然后从字符串中将要查询的表、列和各种查询条件都提取出来，本质上是对一个SQL语句编译的过程，涉及 **词法解析、语法分析、预处理器** 等

- **词法分析：** 就是把一个完整的SQL语句分割成一个个的字符串
- **语法分析：** 根据词法分析的结果做语法检查，判断你输入的SQL语句是否满足SQL语法，如果语法有误，就会收到来自MySQL服务器提示“You have an error in your SQL syntax...”，要关注的是紧接“use near”的内容
- **预处理器：** 预处理器则会进一步去检查解析树是否合法，比如表名是否存在，语句中表的列是否存在等，在这一步MySQL会检验用户是否有表的操作权限



##### ④ 优化SQL

​	对查询进行优化，作用是根据解析树生成不同的执行计划，然后选择最优的执行计划

​	MySQL 里面使用的是基于成本模型的优化器，哪种 **执行计划Explain** 执行时成本最小就用哪种。而且它是io_cost和cpu_cost的开销总和，它通常也是我们评价一个查询的执行效率的一个常用指标



**优化器可以做哪些优化**

1. 当有多个索引可用的时候，决定使用哪个索引
2. 在一个语句有多表关联(join)的时候，决定各个表的连接顺序，以哪个表为基准表

 注意：优化器作用很有限，我们的SQL语句不能依赖于MySQL的优化器去调优，如果SQL语句垃圾，那优化器也没有优化空间，比如优化器可以决定使用哪个索引，但要是数据表根本就没有索引那也谈不上使用索引了，优化SQL的根本在于掌握MySQL分析与调优知识





##### ④ 执行SQL语句

###### 	判断执行权限

​	开始执行SQL语句之前，MySQL会先判断一下当前连接的用户对于要操作的数据表有没有相应的权限，如果没有，就会返回没有权限的错误

###### 	调用存储引擎接口查询

​	如果有权限，就是用指定的存储引擎打开表开始查询，执行器会根据表的引擎定义，去使用这个引擎提供的查询接口提取数据

- **主键执行流程**
  - 调用InnoDB引擎接口，从主键索引中检索对应的值
  - 主键索引等值查询只会查询出一条记录，直接将该记录返回客户端，语句执行结束
- **不是主键执行流程(意味着会进行全表扫描)**
  - 调用InnoDB引擎接口取这个表的第一行，判断对应的列上的值是否与条件相等，如果不相等则跳过，如果相等则将这行结果缓存到结果集中；
  - 调用引擎接口取“下一行”，重复相同的判断逻辑，直到取到这个表的最后一行
  - 执行器将上述遍历过程中所有满足条件的行组成的结果集返回给客户端





### 存储引擎

 	MySQL默认存储引擎是InnoDB，这是一种支持事务的存储引擎，一般我们都不会更改存储引擎，可通过“show engines”查看MySQL支持的存储引擎列表

```sql
show engines;
```

| 存储引擎   | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| **InnoDB** | **5.5版本后MySQL默认数据库存储引擎，支持事务和行级锁**，比MyISAM处理速度稍慢 |
| **MyISAM** | 高速引擎，拥有较高的插入，查询速度，但 **不支持事务**        |
| ISAM       | MyISAM的前身，MySQL5.0以后不再默认安装                       |
| MRG_MyISAM | 将多个表联合成一个表使用，在超大规模数据存储时很有用         |
| **Memory** | **内存存储引擎，拥有极高的插入，更新和查询效率**<br/>但是会占用和数据量成正比的内存空间，只在内存上保存数据，意味着数据可能会丢失 |
| Falcon     | 一种新的存储引擎，支持事务                                   |
| Archive    | 将数据压缩后进行存储，非常适合存储大量的独立的、作为历史记录的数据，但是只能进行插入和查询操作 |
| CSV        | CSV引擎是基于CSV格式文件存储数据(应用与跨平台的数据交换)     |

> Falcon是MySQL的一个实验性的存储引擎，旨在提高写入性能。Falcon在设计时确实支持事务处理能力。然而，值得注意的是，尽管Falcon最初被认为是InnoDB的一个潜在替代品，但该项目并没有成为主流的MySQL存储引擎，并且其开发已经停滞多年。
>
> 最新的MySQL版本中并未包含Falcon存储引擎。由于缺乏维护和更新，如果你正在考虑使用Falcon，可能会遇到兼容性和稳定性问题。因此，对于生产环境来说，使用官方支持并且经过充分测试的存储引擎，如InnoDB，通常是一个更好的选择。

总结就是：**除非需要用到某些InnoDB不具备的特性，并且是在没有其他办法可以代替的情况下，才应该考虑更换存储引擎**



#### InnoDB VS MyISAM

| 比较     | InnoDB                                    | MyISAM                                           |
| -------- | ----------------------------------------- | ------------------------------------------------ |
| 存储文件 | .frm表定义文件<br/>.ibd数据文件和索引文件 | .frm表定义文件<br/>.myd数据文件<br/>.myi索引文件 |
| 锁       | 表锁、行锁                                | 表锁                                             |
| 事务     | 支持                                      | 不支持                                           |
| CRUD     | 读写均可，系统崩溃后数据自动恢复          | 读多写少，崩溃后数据无法安全恢复                 |
| 索引结构 | B+ Tree                                   | B+ Tree                                          |

只有InnoDB存储引擎支持事务、行锁、表锁

在选择时尽可能使用InnoDB引擎

- MyISAM: 早期版本默认的存储引擎
- Memory所有的数据都是保存在内存中

```sql
-- 使用其他存储引擎，在mysql中默认使用InnoDB，一个数据库中不同的表可以使用不同的引擎
create table u_salary(s_id bigint PRIMARY KEY...) engine=myisam;
```



#### InnoDB

**架构图**

![](image/innoDB.png)

- **内存结构**
  1. Buffer Pool缓冲池
  2. Change Buffer修改缓冲
  3. Adaptive Hash Index 自适应哈希索引
  4. Log Buffer日志缓冲
- 磁盘结构
  1. System TablesSpace系统表空间
  2. File-per-table TablesSpaces独立表空间
  3. General TablesSpaces通用表空间



##### 内存结构

###### 【内存结构】缓冲池-Buffer Pool

- **缓冲池Buffer Pool用于加速数据的访问和修改**

- 默认大小为128M

- 缓存数据到内存，最大限度减少磁盘IO，加速热点数据的读和写

- 由于内存有限，Buffer Pool仅能容纳最热点的数据

- 使用LRU(Least Recently Used)算法(最近最少使用)淘汰非热点数据页

  - LRU：根据页数据的历史访问来淘汰数据， **如果数据最近被访问过，那么将来被访问的几率也更高**， 优先淘汰最近没有被访问到的数据

  > MySQL的InnoDB存储引擎在其Buffer Pool中使用了一种经过改良的LRU（Least Recently Used，最近最少使用）算法。传统的LRU算法简单地将最近最不常使用的数据从缓存中移除，以便为新的数据腾出空间。然而，在数据库系统中，这种简单的策略可能不够高效。
  >
  > InnoDB对LRU算法进行了以下改进：
  >
  > 1. **适应性哈希索引**（Adaptive Hash Index, AHI）：
  >    - 除了LRU列表之外，InnoDB还会根据访问模式自动创建和维护一个哈希索引。
  >    - 哈希索引可以提供O(1)的查找性能，加速某些查询。
  > 2. **双链表结构**：
  >    - InnoDB的LRU列表实际上是一个双链表结构，包含两个区域：New（新）区域和Old（旧）区域。
  >    - New区域用于存放最近访问的数据，而Old区域则存放较早访问但还不是很老的数据。
  >    - 当Buffer Pool满时，会先尝试从Old区域淘汰数据，而不是直接从LRU头部开始淘汰。
  > 3. **缓冲池预读机制**：
  >    - InnoDB的预读机制会在读取某个页时预测性地将相邻的页也加载到Buffer Pool中。
  >    - 这种机制有助于减少磁盘I/O，提高性能。
  > 4. **老化区**：
  >    - InnoDB引入了一个老化区的概念，当某一页被移除LRU列表后，并不会立即释放该页的内存，而是放入老化区等待一段时间。
  >    - 如果在老化期间有其他请求需要访问这个页，则它会被重新放回LRU列表，避免了不必要的磁盘I/O。
  > 5. **脏页管理**：
  >    - InnoDB中的LRU列表同时包含了干净页和脏页。
  >    - 脏页是指在Buffer Pool中被修改过的页，它们需要定期刷新到磁盘以确保数据一致性。
  >
  > 这些改进使得InnoDB的LRU算法能够更好地适应数据库的工作负载，降低磁盘I/O，提高整体性能。

- Buffer Pool中的数据 **以页为存储单位**， 数据结构是 **单向链表**



###### 【内存结构】修改缓存-ChangeBuffer

- **用于加速 <font color=#f00>非热点数据</font> 中二级索引的写入操作**
- 修改缓冲对二级索引的修改操作会录入 redo log中
- 在缓冲到一定量或系统较空闲时进行merge操作(写入磁盘)
- 修改缓冲在系统表空间中有相应的持久化区域
- 物理结构为一颗名为ibuf的B+树



###### 【内存结构】自适应哈希索引-Adaptive Hash Index

- 用于实现对于热数据页的一次查询， **是建立在索引之上的索引**

> 自适应哈希索引（AdaptiveHashIndex，AHI） **用于实现对于热数据页的一次查询。** 是建立在索引之上的索引！使用聚簇索引进行 **数据页定位** 的时候需要根据索引树的高度从根节点走到叶子节点，**通常需要3到4次查询才能定位到数据**。InnoDB根据对索引使用情况的分析和索引字段的分析，通过自调优Self-tuning的方式为索引|顶建立或者删除哈希索引。
>
> **AHI的大小为BufferPool的1/64，**在MySQL5.7之后支持分区，以减少对于全局AHI锁的竞争，默认分区数为8。
>
> AHI所作用的目标是 **频繁查询的数据页和索引页**， 而由于数据页是聚簇索引的一部分，因此AHI是建立在索引之上的索引，**对于二级索引，若命中AHI，则将直接从AHI获取二级索引I页的记录指针，再根据主键沿着聚簇索引查找数据；若聚簇索引查询同样命中AHI，则直接返回目标数据页的记录指针，此时就可以根据记录指针直接定位数据页。**

- **作用： 对频繁查询的数据页和索引页进一步提速**

**相关SQL命令**

```sql
-- 查看InnoDB存储引擎状态，包含自适应哈希状态信息(MySQL8已移除该命令)
show engine innodb status;

-- 查看是否开启自适应哈希配置，默认是开启的
show variables like 'innodb_adaptive_hash_index';
```





###### 【内存结构】日志缓冲-Log Buffer

- InnoDB使用Log Buffer来缓冲日志文件的写入操作
-  **内存写入** 加上 **日志文件顺序写** 使得InnoDB日志写入性能极高

![](image/logbuffer.png)

通过上图我们发现，所有的操作都是基于内存的，那么出现故障后应该怎么处理呢？这也是InnoDB遇到的一个飞车重要的问题





##### 磁盘结构

​	在磁盘中，InnoDB存储引擎将数据、索引、表结构和其他缓存信息等存放的空间称为表空间(TableSpace)，它是物理存储中的最高层，由段Segment、区extent、页Page、行Row组成



**常见表空间**

系统表空间(System Tablespace)，关闭独立表空间，所有表数据和索引都会存入系统表空间

独立表空间(File-per-table tablespace)，开启独立表空间，每张表的数据都会存储到一个独立表空间

通用表空间(General Tablespace)

回滚表空间(Undo Tablespace)

临时表空间(The Temporary Tablespace)



###### 【磁盘结构】系统表空间-System Tablespace

- 系统表空间是InnoDB 数据字典、 **双写缓冲**、 **修改缓冲** 和 **回滚日志** 的存储位置如果关闭独立表空间，它也将存储所有表数据和索引

  - 数据字典： 表对象的元数据信息（表结构、索引、列信息等等）
  - **双写缓冲：** 保证写入数据时，页数据的完整性，防止部分写失效问题
  - **修改缓冲：** 内存中changeBuffer对应的持久化区
  - **回滚日志：** 实现数据回滚时对数据的恢复，MVCC实现的重要组成

- 默认大小12M，文件名称ibdata1，配置参数：innodb_data_file_path

- 系统表空间会自动增长，每次增量64MB，且增长之后的文件不可缩减

- ```sql
  -- 查看系统表空间
  show variables like 'innodb_data_file_path';
  ```





###### 【磁盘结构】独立表空间-File-per-table Tablespace

- **独立表空间用于存放每个表的数据和索引。在5.7版本中默认开启，初始化大小是96KB**
- 其他类型的信息，如：回滚日志、双写缓冲区、系统事务信息、修改缓冲等仍存放于系统表空间内，因此即使用了独立表空间，系统表空间也会不断增长
- 开启关闭：innodb_file_per_table=ONlOFF
- 在数据库文件夹内，为每张表单独创建表空间 **表名.ibd** 文件荐数据写索引l





###### 【磁盘结构】通用表空间-GeneralTablespace

- 通用表空间存在的目的是为了在系统表空间与独立表空间之间作出平衡
- 系统表空间与独立表空间中的表可以向通用表空间移动，反之亦可





【磁盘结构】回滚表空间-Undo Tablespace

- UndoTableSpace 用于存放一个或多个undolog 文件，默认大小为10MB
- Undolog默认存储在系统表空间，在5.7版本中支持自定义到独立的表空间





###### 【磁盘结构】临时表空间-TheTemporaryTablespace

- MySQL5.7之前临时表存储在系统表空间，这样会导致ibdata在使用临时表的场景下疯狂增长
- MySQL5.7版本里InnoDB引擎从系统表空间中抽离出临时表空间独立保存临时表数据
- 配置属性：innodb_temp_data_file_path





##### 表空间存储结构

![](image/tablespace.png)

**<font color=#f00>表空间由段（Segment）、区（extent）、页（Page）、行（Row）组成</font>**



###### 01-段(Segment)

- 表空间由各个段组成，段类型分数据段、索引段、回滚段
- MySQL的索引数据结构是B+树，这个树有叶子节点和非叶子节点
- 一个段包含多个区，至少有一个区，段扩展的最小单位是区
  -  **数据段** 是叶子节点Leafnodesegment
  -  **索引段** 是非叶子节点Non-Leafnodesegment



###### 02-区(Extent)

- 区是由连续的页组成的空间，大小固定为1MB
- 默认情况下，一个区里有64个页
- 为了保证区的连续性，InnoDB一次会从磁盘申请4-5个区



###### 03-页(Page)

-  **页是InnoDB 的基本存储单位，** 页默认大小是16K(可配置innodb_page_size，不建议更改，单位是字节)，InnoDB首次加载后便无法更改
- **操作系统读写磁盘最小单位是页，大小是4KB(4096字节)**
- 磁盘存储数据量最小单位是512字节(byte)



###### 04-行(Row)

- InnoDB的数据是以行为单位存储，一个页中包含多个行
- InnoDB提供4种行格式：Compact、Redundant、Dynamic和Compressed
- 默认行格式Dynamic

```sql
-- 创建表时指定行格式
CREATE TABLE user(u_id bigint AUTO_INCREMENT) ROW_FORMAT=DYNAMIC;

-- 修改表的行格式
ALTER TABLE tablename ROW_FORMAT=行格式名称;

-- 修改默认行格式
SET GLOBAL innodb_default_row_format=行格式名称;

-- 查看表的行格式
SHOW TABLE STATUS LIKE '表名';
```



##### 内存数据落盘

###### 脏页

​	1、**什么是脏页？**

​	对数据的 **修改操作**， 首先修改 **<font color=#f00>内存结构中缓存区</font>** 的页， **缓存区** 中的页与 **磁盘** 中的页数据不一致，所以称为缓冲区中的页为 **脏页**

​	2、**脏页如何进入到磁盘的？** 

​	脏页从缓冲区刷新到磁盘，不是每次页更新之后触发，而是通过 **CheckPoint机制** 刷新到磁盘的

![](image/RedoLog.png)



3、**如何提升性能？**

- **内存中写，** 操作过程 **记录日志**， 日志批量写入磁盘
- 分散写变顺序写，减少了磁盘空间寻址

4、**如何持久化数据？**

- 通过MySQL的 **CheckPoint机制** 进行 **脏页落盘**
-  **日志先行**， 所有操作前先写Redo日志

> **MySQL的Checkpoint机制是数据库管理系统中的一种重要机制，主要用于确保数据的一致性和持久性**。在InnoDB存储引擎中，Checkpoint主要涉及以下几个方面：
>
> 1. **脏页刷新**：
>    - 当数据库中的数据被修改后，这些修改首先发生在内存中的数据页（称为“脏页”），并不会立即写回磁盘。
>    - Checkpoint过程就是将这些脏页定期或在某些特定条件下刷新到磁盘上，确保内存中的更改最终会被永久保存。
> 2. **Write Ahead Log (WAL) 策略**：
>    - InnoDB采用WAL策略来保证数据的一致性和防止数据丢失。
>    - 在事务提交时，先将修改操作记录在重做日志（Redo Log）中，然后再修改内存中的数据页。
>    - 这样即使系统发生故障，也可以通过重做日志恢复未写入磁盘的更改。
> 3. **Checkpoint类型**：
>    - InnoDB中有多种类型的Checkpoint，包括以下几种：
>      - **Sharp Checkpoint**：一次性将所有脏页全部刷新到磁盘，可能会导致大量的I/O操作和性能下降。
>      - **Fuzzy Checkpoint**（也称为Incremental Checkpoint）：逐步地、增量地将脏页刷新到磁盘，可以降低对系统性能的影响。
>      - **Background Checkpoint**：由后台线程Master Thread异步执行，定期将一部分脏页刷新到磁盘。
>      - **FLUSH_LRU_LIST Checkpoint**：在InnoDB早期版本中，当LRU列表中需要有足够的空闲页时，会触发这种Checkpoint。
> 4. **Checkpoint触发条件**：
>    - 日志空间不足：当重做日志空间即将用尽时，会触发Checkpoint，以便清空重做日志并循环使用。
>    - 时间间隔：Master Thread按照固定的频率执行Checkpoint，例如每秒或每十秒。
>    - 缓冲池大小阈值：当缓冲池中的脏页数量达到一定比例或数量时，可能触发Checkpoint。
> 5. **目的和作用**：
>    - 保证数据一致性：通过将内存中的更改持久化到磁盘，确保在系统崩溃或重启时能够恢复到一致的状态。
>    - 避免日志覆盖：通过定期刷新脏页，释放重做日志空间，防止新的事务因为日志空间不足而无法进行。
>    - 管理缓冲池：通过Checkpoint控制缓冲池中的脏页数量，优化内存使用和I/O操作。
>
> 总的来说，MySQL的Checkpoint机制是一个复杂的过程，旨在平衡数据安全性、系统性能和资源利用率。通过精细的管理和调度，Checkpoint能够在保证数据一致性和系统稳定性的前提下，尽可能地降低对系统性能的影响。

5、**安全性怎么保证？**

- Force Log at Commit(提交时强制记录)
  - 提交事务前日志必须写入到磁盘中去，不然事务是无法成功提交的
- Write Ahead Log(WAL)(日志先行)
  - 所有操作前先记录Redo日志
- CheckPoint机制
- Double Write机制

6、**为什么不是每次更新都直接写入磁盘？**

- 如果每次页发生改变就落盘，一个页落盘必然伴随多次IO操作，性能开销很大，而且随着写入操作增加，性能开销也会呈指数级增长

![](image/RedoLogIO.png)

- 当然，数据也不能在内存中保存太长时间， **时间越久安全性风险就越高**
- InnoDB采用 **WriteAheadLog** 策略和 **ForceLog at Commit** 机制实现事务的持久性
  - Write Ahread Log: 日志先行，数据变更写入到磁盘前，必须先将内存中的日志写入到磁盘中
  - Force Log at Commit: 当事务提交时，所有事务产生的日志都必须刷到磁盘中



7、**怎么确保日志安全进入磁盘？**

为了确保日志写入到磁盘，将Redo日志写入Log Buffer后调用fsync函数，将缓冲日志文件从OS Cache中写入磁盘

**Redo日志默认落盘策略，事务提交立即落盘。** Log Buffer写入磁盘的时机由参数innodb_flush_log_at_trx_commit控制，可通过一下命令查看：show VARIABLES like 'innodb_flush_log_at_trx_commit'，此参数控制每次事务提交时InnoDB的行为

**三个配置：**

-  **为0时：** 每秒写入，与事务无关
  - 系统崩溃时最多丢失1秒的事务操作
  - 写入效率最高，安全性最低
-  **为1时：** 事务提交时，写入OS缓存并刷新到磁盘
  - 不会丢失数据
  - 写入效率最低，安全性最高
-  **为2时：** 事务提交时，写入OS缓存，固定间隔时间刷新到磁盘
  - 数据安全性依赖于系统，最多丢以1秒事务操作
  - 写入效率居中，安全性居中 



###### CheckPoint机制

​	它是将缓冲池中的脏页数据刷到磁盘上的机制，决定脏页落盘的时机、条件和脏页的选择等，CheckPoint的类型不止一种

**解决什么问题？**

- **脏页落盘：** 避免数据更改直接操作磁盘
- **缩短数据库的恢复时间：** 数据库宕机时，不用重做所有Redo日志，大大缩短恢复时间
- **缓冲池不够用时，将脏页刷新到磁盘：** Buffer Pool不够用时溢出页落盘，LRU淘汰掉的非热点数据
- **Redo日志不可用时，刷新脏页：** 日志文件可以循环使用，不会无限增长

**分类：**

- **sharp checkpoint:** 关闭数据库时将脏页全部刷新到磁盘中
- **fuzzy checkpoint:** **默认方式**， 在运行时选择不同时机将脏页刷到磁盘中，只刷新部分脏页 
  - **Master Thread Checkpoint:** 固定频率刷新部分脏页到磁盘，异步操作不会阻塞用户线程
  - **FLUSH_LRU_LIST Checkpoint:** 缓冲池淘汰非热点Page，如果Page是脏页会执行CheckPoint
  - **Async/Sync Flush Checkpoint:** Redo日志不可用时，强制脏页落盘，有了前两个这种一般不会发生
  - **Dirty Page too much Checkpoint:** 脏页占比太多强制进行刷盘，阈值75%



###### Double Write机制

**写失效问题**

​	数据库准备刷新脏页时，将16KB的数据刷入磁盘，但写入到8KB时，就发生系统宕机了，这种只写了部分没完成的情况被称为 **写失效 Partial Page Write** 



DoubleWrite其实就是写两次，在修改记录Redo日志前，先做个副本留个“备胎”。 **Redo日志不能解决写失效问题，因为Redo日志记录的是对页的修改记录而不是数据本身** 

![](image/doubleWrite.png)



##### 事务

- 指的是逻辑上一个组操作，这组操作的组成单元 **要么全都成功，要么全都失败**

- 本质上是个 **并发编程问题**
- 客户端与MySQL建立连接后，一个连接开启一次会话，每个会话中可以多次开启和提交事务
- 事务是由存储引擎实现，MySQL中只有InnoDB支持事务



###### 四大特性ACID

- 原子性(Atomicity)：事务最小工作单元，要么全成功，要么全失败
- 一致性(Consistency)：事务前后数据的完整性必须保持一致
- 隔离性(Islolation)：多个用户并发使用数据库时，彼此事务操作数据不能互相干扰，所以要隔离。
- 持久性(Durability)：事务一旦提交，它对数据的改变就是永久性的



###### 事务并发问题

1. 脏读：一个事务读到了另一个事务 **未提交** 的数据
2. 不可重复读： 一个事务读到另一个事务 **已经update** 的数据。引发事务中的多次查询结果不一致
3. 虚读/幻读： 一个事务读到另一个事务 **已经Insert** 的数据。导致事务中多次查询的结果不一致

> 在MySQL中，幻读（Phantom Read）是指在一个事务中，当执行相同的SELECT查询两次时，第二次查询返回的结果集中包含了第一次查询未出现的新行。这些“幻影”行是由于在当前事务进行过程中，其他事务插入了满足查询条件的新数据并提交了这些更改。
>
> 以下是一个简单的例子来说明幻读：
>
> 1. 事务A开始。
> 2. 事务A执行SELECT语句，查询所有年龄大于20的用户：`SELECT * FROM users WHERE age > 20;` 这个查询返回了10条记录。
> 3. 在事务A还未结束时，事务B开始并插入了一个新的用户，其年龄为25，并提交了事务。
> 4. 事务A再次执行相同的SELECT语句：`SELECT * FROM users WHERE age > 20;` 这次查询返回了11条记录，因为包含了事务B新插入的用户。
>
> 在可重复读（Repeatable Read）隔离级别下，MySQL的多版本并发控制（MVCC）可以防止不可重复读的问题，即在同一事务内多次读取同一数据时结果保持一致。但是，MVCC并不能完全防止幻读，因为在可重复读隔离级别下，ReadView只关注已存在的记录，而不考虑在事务执行期间新插入的记录。
>
> 要解决幻读问题，可以采取以下策略：
>
> - 提高事务隔离级别到串行化（Serializable）：在串行化隔离级别下，数据库会使用更严格的锁机制来确保事务之间的完全隔离，从而避免幻读的发生。但是，这可能会导致并发性能下降，因为事务之间需要等待彼此完成才能获取锁。
> - 使用显式锁定：在可重复读隔离级别下，可以通过在SELECT语句中添加`FOR UPDATE`或`LOCK IN SHARE MODE`子句来获取适当的锁，以防止其他事务插入满足查询条件的新行。但这需要对应用程序有深入的理解和精细的控制，以避免死锁和过度锁定。
>
> 总的来说，理解并处理幻读问题是保证数据库事务一致性的重要部分。根据具体的应用场景和性能要求，可以选择合适的隔离级别和锁定策略来平衡数据一致性与并发性能。





###### 事务隔离级别

-  **读未提交RU：** Read Uncommitted，一个事务读到另一个事务没有提交的数据
  - 存在三个问题：脏读、不可重复读、幻读
-  **读已提交RC：** ReadCommitted，一个事务读到另一个事务已经提交的数据
  - 存在两个问题：不可重复读、幻读
-  **可重复读RR：** Repeatable Read，在一个事务中读到的数据始终保持一致，无论另一个事务是否提交
  - 存在一个问题：幻读（MySQL通过锁的方式解决了这个问题，实际上测试不出来）
-  **串行化读：** Serializable，同时只能执行一个事务，相当于事务中的单线程，自然啥问题都没有了

**性能：** 	串行化读 < 可重复读RR < 读已提交RC < 读未提交RU

**安全性：** 串行化读 > 可重复读RR > 读已提交RC > 读未提交RU

**具体应用场景**

1. **READ UNCOMMITTED（读未提交）**
   - **应用场景**：在非常强调性能，可以容忍一定程度的数据不一致性的场景下，可能会使用这一级别。例如，在某些实时分析系统中，如果短暂的读取到其他事务未提交的“脏”数据并不会严重影响最终结果的准确性，那么可以选择这一级别来提升读取速度。
   - **举例**：假设两个事务同时运行，事务A正在修改一行数据但还未提交，事务B使用了READ UNCOMMITTED级别，则事务B可以在事务A提交前读取到事务A修改但未提交的数据，这便是所谓的“脏读”。
2. **READ COMMITTED（读已提交）**
   - **应用场景**：适合那些希望避免脏读，但对于非关键业务允许出现不可重复读和幻读的情况。许多商业数据库系统的默认隔离级别就是READ COMMITTED。
   - **举例**：事务A首先读取一条记录，然后事务B对该记录进行修改并提交，这时事务A再次读取同一条记录时，会看到事务B已经提交的修改，这就是不可重复读。在银行系统中，如果两个查询相隔很短，一个查询显示账户余额后，另一个查询再次检查余额以确认转账前的状态，若使用READ COMMITTED级别则可能出现余额值不一致。
3. **REPEATABLE READ（可重复读）**
   - **应用场景**：主要用于那些要求在一个事务内多次读取同一份数据时，结果始终保持一致，不允许出现不可重复读的场景，例如金融交易中的审计操作，需要确保事务开始后读取的数据在整个事务期间不会发生改变。
   - **举例**：在REPEATABLE READ级别下，如果事务A在开始后读取了一条记录，即使事务B在这之后修改了这条记录并提交了事务，事务A在事务期间内再次读取该记录时，仍然能看到第一次读取时的数据状态，不会受到事务B的影响。然而，事务A在读取一个范围的记录后，事务B插入的新记录（符合查询条件）在事务A后续的查询中会出现，这称为幻读。
4. **SERIALIZABLE（可串行化）**
   - **应用场景**：在需要严格的数据一致性，不容忍任何并发问题的场景下使用，例如银行转账操作，必须确保资金在转出和转入过程中绝对准确无误，不存在任何并发冲突。
   - **举例**：在SERIALIZABLE级别下，事务在执行过程中仿佛被串行化安排，每个事务都在单独的队列中按序执行。如果有多个事务试图同时修改相同的数据，其中一个事务必须等待另一个事务结束才能继续执行。这种严格的隔离方式可以完全避免脏读、不可重复读以及幻读问题，但代价是可能导致并发性能下降和更多的锁竞争。例如，在一个库存管理场景中，事务A减少库存的同时，事务B尝试增加相同的库存，如果没有足够的并发控制，可能会导致库存数量不准确，但在SERIALIZABLE级别下，可以确保这样的操作按照一定的顺序执行，避免并发问题。



###### 一条Insert语句的执行流程

```sql
Insert into tab_user(id,name,age,address) values (1,'刘备',18,'蜀国');
```

![](image/insertExecute.png)

​	

**在没有事务的情况下这个执行流程中，会产生什么问题？**

​	**丢失更新问题**

​	两个事务针对同一数据进行修改操作时会丢失更新，这个现象称之为丢失更新问题

​	 举个栗子：管理者查询所有用户的存款总额，假设除了用户01和用户01之外，其他用户的存款都为0， 用户01、02各有存款1000，所以所有用户的存款总额为2000。但是在查询过程中，用户01会向用户02 进行转账操作

![](image/example1.png)



​	**如何解决**

- 基于锁并发数控制 **LBCC(Lock Based Concurrency Control)：** 查询总额的事务对读取数据行加锁，等读取数据结束后释放锁
- 基于版本并发控制 **MVCC(Multi Version Concurrency Control)：** 查询总额的事务不加锁，但是会读取事务开始时的快照数据



​	基于LBCC的时序图

​	**说明：** 查询总额事务会对读取的行加锁，等到操作结束后再释放所有行上的锁。因为用户A的存款被锁，导致 转账操作被阻塞，直到查询总额事务提交并将所有锁都释放

![](image/example2.png)



​	基于MVCC的时序图

​	**说明：** 查询总额事务先读取了用户A的账户存款，然后转账事务会修改用户A和用户B账户存款，查询总额事务 读取用户B存款时不会读取转账事务修改后的数据，而是读取本事务开始时的副本数据【快照数据】。

![](image/example3.png)

**MVCC使得普通的SELECT请求不加锁，读写不冲突，显著提高了数据库的并发处理能力。**



###### MVCC机制

- MVCC(Multi-Version Concurrency Control)全称叫多版本并发控制，是RDBMS常见的一种并发控制方法，用来对数据库数据进行并发访问，实现事务。 **核心思想：读不加锁，读写不冲突**， MVCC 实现原理关键在于数据快照，不同的事务访问不同版本的数据快照，从而实现事务下对数据的隔离级别。虽然说具有多个版本的数据快照，但这并不意味着必须拷贝数据，保存多份数据文件（这样会 浪费存储空间），InnoDB通过事务的Undo日志巧妙地实现了多版本的数据快照
- InnoDB下的表有默认字段和可见字段，默认字段是实现MVCC的关键，默认字段是隐藏的列。默认字段 最关键的两个列，一个保存了行的事务ID，一个保存了行的回滚指针。每开始新的事务，都会自动递增 产生一个新的事务id。事务开始后，生成当前事务影响行的ReadView。当查询时，需要用当前查询的事 务id与ReadView确定要查询的数据版本
- 实现原理： **数据快照**，不同事务访问数据快照中不同版本的数据
- 关键要素： Undo日志和ReadView



###### Undo日志

- Redo日志记录了事务的行为，可以很好地通过其对页进行“重做”操作。但是事务有时还需要进行回滚操 作，这时就需要undo。因此在对数据库进行修改时，InnoDB存储引擎不但会产生Redo，还会产生一定量的Undo。这样如果用户执行的事务或语句由于某种原因失败了，又或者用户用一条Rollback语句请求 回滚，就可以利用这些undo信息将数据回滚到修改之前的样子。在多事务读取数据时，有了Undo日志 可以做到读不加锁，读写不冲突
- Undo存放在数据库内部的一个特殊段（segment）中，这个段称为Undo段（undo segment）。Undo 段位于系统表空间内，也可以设置为Undo表空间
- Undo日志保存了记录修改前的快照。所以，对于更新和删除操作，InnoDB并不是真正的删除原来的记 录，而是设置记录的delete mark为1。因此为了解决数据Page和Undo日志膨胀问题，则需要回收机制 进行清理Undo日志

- 在对数据修改时，InnoDB会产生一定量的Undo日志，如果事务执行失败RollBack了，则利用Undo日志回滚到历史版本
- Undo日志还可以解决 **丢失更新问题**
- 存储位置：系统表空间内的回滚段中
- 日志分类：
  - Insert Undo日志：是在Insert操作中产生的Undo日志
  - Update Undo日志： 是Update或Delete操作中产生的Undo日志



###### ReadView

在MySQL的多版本并发控制（MVCC）中，ReadView是一个重要的概念，它用于在特定的事务隔离级别下保证数据的一致性和可见性。

当一个事务开始时，MySQL会根据事务的隔离级别创建一个ReadView。这个ReadView包含了以下关键信息：

1. **m_ids**：这是一个活跃事务的ID列表，这些事务在ReadView创建时还未提交。如果一个数据行的事务ID在这个列表中，那么这个数据行对于当前事务是不可见的，因为它是被其他活跃事务修改的。
2. **low_limit_id** 和 **up_limit_id**：这两个值定义了一个范围，用来判断数据行的事务ID是否在ReadView的可见范围内。具体来说：
   - **low_limit_id** 是小于或等于ReadView创建时刻所有已分配事务ID中的最小值。
   - **up_limit_id** 是大于ReadView创建时刻所有已分配事务ID中的最大值。

当一个事务需要读取一行数据时，它会使用ReadView来确定该行数据的可见性：

- 如果数据行的事务ID小于**low_limit_id**，那么这个数据行是在ReadView创建之前提交的，因此对当前事务是可见的。
- 如果数据行的事务ID大于**up_limit_id**，那么这个数据行是在ReadView创建之后才开始的事务修改的，因此对当前事务不可见。
- 如果数据行的事务ID在**low_limit_id**和**up_limit_id**之间，那么需要检查数据行的事务ID是否在**m_ids**列表中。如果在列表中，说明这个数据行是由其他活跃事务修改的，对当前事务不可见；如果不在列表中，那么数据行是在ReadView创建之前提交的，对当前事务可见。

通过这种方式，MVCC和ReadView能够在不使用锁的情况下实现并发读取，并确保每个事务看到的数据视图是一致的，从而提高了数据库的并发性能。在可重复读（Repeatable Read）隔离级别下，MySQL会为每个事务创建一个ReadView，并在整个事务期间使用这个ReadView来决定数据的可见性。而在读已提交（Read Committed）隔离级别下，每次SELECT操作都会创建一个新的ReadView。

- ReadView是张存储事务ID的表，主要包含当前系统中有哪些活跃的读写事务id，结合Undo日志的默认字段[trx_id]来控制哪个版本的Undo日志可被其它事务看见
- 四个列：
  - **m_ids：** 生成ReadView时，当前 **活跃的事务id列表**
  - **m_low_limit_id：** **事务id下限，** 当前活跃事务中最小的事务id
  - **m_up_limit_id：**  **事务id上限，** 生成ReadView时，应该分配给下一个事务的id值
  - **m_creator_trx_id：** 生成该ReadView的事务的事务id
- 生成ReadView的时机
  - 开启事务之后，在第一次查询时，生成ReadView
  - RC和RR隔离级别的差异本质是因为ReadView的生成时机不同



###### 事务实现原理

​	MySQL的InnoDB存储引擎在实现事务的隔离性时，确实使用了MVCC（Multi-Version Concurrency Control，多版本并发控制）机制。但是，整个事务的实现不仅仅依赖于MVCC，还包括redo log和undo log等组件的配合。

- MVCC主要用于处理读操作的并发控制，它通过维护数据的多个版本来实现不同事务之间的隔离，使得每个事务能够看到一个一致性的快照，从而避免了加锁带来的性能问题。在InnoDB中，每行数据都有隐藏的系统字段（如DB_TRX_ID、DB_ROLL_PTR和DB_ROW_ID等），用于记录行的创建和删除信息，以此来实现多个版本的并存。
- redo log（重做日志）主要用于保证事务的持久性和原子性。当事务对数据进行修改时，首先将修改操作记录到redo log中，然后才修改内存中的数据。在事务提交时，redo log会被刷新到磁盘。如果系统发生故障，可以通过redo log恢复未写入磁盘的数据，确保事务的更改不会丢失。
- undo log（回滚日志）主要用于支持事务的原子性和一致性。它记录了数据的旧值，以便在事务回滚时能恢复到修改前的状态。同时，undo log也在MVCC中发挥作用，用于构建历史版本的数据。

因此，MySQL事务的底层实现是redo log、undo log和MVCC等多种机制的综合运用，共同确保了事务的ACID特性（Atomicity、Consistency、Isolation、Durability，即原子性、一致性、隔离性和持久性）。



###### MVCC读操作

​	在MVCC中，读可以分为两类： **快照读Snapshot Read与当前读Current Read**

- **快照读：**
  - 读不加锁，读取的是 **版本链** 的快照数据，默认的读都是快照读
- **当前读：**
  - 读加锁，读取的永远都是最新数据，保证其他事务不会再并发修改这条记录

```sql
# 默认都是快照读
select * from user where ?;

# 加读锁(S锁)
select * from user where ? lock in share mode;

# 加写锁(X锁)
select * from user where ? for update;
```

**具体流程**

- 当Update SQL操作进入MySQL后：
  1. MySQL Server根据where条件，先从InnoDB读回第一条符合条件记录，采用的是当前读
  2. MySQL Server收到记录，并对该记录加写锁
  3. 加锁后发起Update操作更新该记录
  4. 本条记录操作完毕后，接着读取下一条，然后继续上述操作
  5. 直到没有满足条件的记录为止
  6. 提交事务，释放锁
- Delete和Update执行流程一样，Insert操作会有唯一性约束等规则校验，其它流程都一致





### 索引

- 索引是数据库 **高效获取数据的数据结构**，加快查询速度，索引一般存储在表空间中，也就是磁盘里
- MySQL常见索引： 聚簇索引、辅助索引、组合索引、唯一性索引
- 没有特别说明，一般索引指的是B+树组织结构
- 优势和劣势
  - 优势： 两降一升，降低磁盘IO频次，降低数据排序的成本，提高数据检索效率
    - 被索引列会自动排序(基于B+树叶子节点的有序特性)
    - 如果order by子句的字段使用索引，效率会高很多
  - 劣势： 占用更多磁盘空间(索引本质上就是一种空间换时间的手段)，降低更新效率，频繁更新的字段没建议建立索引



#### 一条SELECT语句的执行流程

```sql
SELECT * FROM user WHERE u_id = 1;
```



![](image/selectExecute.png)



#### 如何使用索引

- 索引类型

  - 单列索引：所以中只有一个列
    - 主键索引： 必须唯一且非空
    - 唯一性索引：必须唯一，可以为空
    - 普通索引：可以不唯一，可以为空
    - 全文索引：支持全文搜索的索引，只能对文本类型字段设置，不建议使用
    - 空间索引：5.7版起支持空间索引，而且支持OpenGIS集合数据模型
    - 前缀索引：用字段的一部分建立索引

  ```sql
  -- 创建普通索引
  CREATE INDEX index_name ON table_name (column1);
  
  -- 创建唯一索引
  CREATE UNIQUE INDEX index_name On table_name(column1);
  ```

  

  - 组合索引：使用2个以上的字段创建的索引
    - 最左原则

  ```sql
  -- 创建组合索引
  ALTER TABLE table_name ADD INDEX index_name(column1, column2);
  
  -- 删除索引
  DROP INDEX index_name ON table_name;
  
  -- 查看索引
  SHOW INDEX FROM table_name;
  ```

  

#### 索引的数据结构

- 使用索引的基本需求
  - 等值查询：根据某个值查找数据
  - 范围查询：根据某个范围区间查找数据
  - 排序 Order By
  - 分组 Group By
  - **性价比：** 时间和空间
    - 执行时间尽可能短
    - 占用磁盘存储空间尽可能少



为什么使用B+树作为索引的数据结构





#### 索引的实现

##### MyISAM

- MyISAM数据文件和索引文件分开存储，索引B+Tree数据结构，其中叶子节点Key为索引列值，数据为所在行的磁盘地址
- 表索引存储在文件tablename.MYI中，数据文件存储在数据文件tablename。MYD中
  -   **主键索引：**  MyISAM查询时会将索引节点缓存在MySQL缓存中，而数据的缓存依赖于操作OS CaChe
  -   **辅助索引：** 主键索引必须唯一，辅助索引可以重复，由于辅助索引允许重复，所以即便是等值查询，也需要按照范围查询的方式在辅助索引树上查询数据



##### InnoDB

- 每个InnoDB表都有一个 **聚簇索引**， 也交聚集索引。除了聚簇索引以外的其它索引都叫辅助索引
- 聚簇索引是B+Tree数据结构，叶子节点储存数据行，非叶子节点存储主键值，在查询时，InnoDB使用主键值在聚簇索引中搜索行记录，一般情况下主键索引就是聚簇索引，如果没有显式定义主键，InnoDB会自动选择一个唯一且非空的索引作为聚簇索引，如果不存在这样的索引，则InnoDB会创建一个隐藏的、系统生成的ROWID作为聚簇索引来进行数据存储。
- InnoDB的表数据和索引默认存储在一个文件tablename.ibd中
- **主键索引**
  - InnoDB要求表 **必须有主键索引**
  - 主键索引 **叶子节点存储数据行，** **<font color=#f00>辅助索引只会存储主键值</font>**
  - 底层叶子节点按照顺序排序
- **辅助索引**
  - InnoDB的辅助索引只会存储主键值
  - 辅助索引查询记录必然经过主键索引：首先查辅助索引获取主键，根据主键索引查询获得记录
  - 叶子节点按顺序排序



###### 主键索引案例

```sql
CREATE TABLE `t_user_innodb` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`username` varchar(20) DEFAULT NULL,
`age` int(11) DEFAULT NULL,
PRIMARY KEY (`id`) USING BTREE,
KEY `idx_age` (`age`) USING BTREE
) ENGINE=InnoDB;
insert into t_user_innodb values(15,'Nick',5);
insert into t_user_innodb values(18,'Zero',22);
insert into t_user_innodb values(20,'Tom',34);
insert into t_user_innodb values(30,'Nick',55);
insert into t_user_innodb values(49,'Mary',22);
insert into t_user_innodb values(50,'James',77);
insert into t_user_innodb values(56,'John',89);
insert into t_user_innodb values(77,'Lily',100);
```



**等值查询**

```sql
select * from t_user_innodb where id=30;
```

![](image/query1.png)

1. 先在主键树中从根节点开始检索，将根节点加载到内存，比较30<56，走左路。（1次磁盘IO） 
2. 将左子树节点加载到内存中，比较20<30<49，向下检索。（1次磁盘IO） 
3. 检索到叶节点，将节点加载到内存中遍历，比较20<30，30=30。查找到值等于30的索引项，直接 可以获取整行数据。将改记录返回给客户端。（1次磁盘IO）





**范围查询**

```sql
select * from t_user_innodb where id between 30 and 49;
```

1. 先在主键树中从根节点开始检索，将根节点加载到内存，比较30<56，走左路（1次磁盘IO） 
2. 将左子树节点加载到内存中，比较20<30<49，向下检索（1次磁盘IO） 
3. 检索到叶节点，将节点加载到内存中遍历比较20<30，30<=30<49。查找到值等于30的索引项。获 取行数据缓存到结果集中（1次磁盘IO） 
4. 向后遍历底层叶子链表，将下一个节点加载到内存中，遍历比较，30<49<=49，获取行数据缓存到 结果集中（1次磁盘IO） 
5.  最后得到2条符合筛选条件，将查询结果集返给客户端



​	**总结：** 因为在主键索引中直接存储了行数据，所以InnoDB在使用主键查询时可以快速获取行数据。 当表很大时，与在索引树中存储磁盘地址的方式相比，因为不用再去磁盘中获取数据，所以聚簇索引通 常可以节省磁盘IO操作



###### 辅助索引案例

​	以表t_user_innodb的age列为例，age索引的索引结果如下图。底层叶子节点的按照（age，id）的顺序 排序，先按照age列从小到大排序，age列相同时按照id列从小到大排序

![](image/query2.png)

**等值查询**

```sql
 select * from t_user_innodb where age=22;
```

1. 先在索引树中从根节点开始检索，将根节点加载到内存，比较22<77，走左路（1次磁盘IO） 
2. 将左子树节点加载到内存中，比较22<34，向下检索（1次磁盘IO） 
3. 检索到叶节点，将节点加载到内存中从前往后遍历比较（1次磁盘IO） 
   - 第一项5：5 < 22不符合要求，丢弃。 
   - 第二项22：等于22，符合要求，获取主键id=18，去主键索引树中检索id=18的数据放入结果 集中。（回表查：3次磁盘IO）
   - 第三项22：等于22，符合要求，获取主键id=49，去主键索引树中检索id=49的数据放入结果 集中。（回表查：3次磁盘IO） 
4. 向后遍历底层叶子链表，将下一个节点加载到内存中，遍历比较。（1次磁盘IO） 
   - 第一项34：34>22不符合要求，丢弃。查询结束 
5. 最后得到2条符合筛选条件，将查询结果集返给客户端



**范围查询**

```sql
select * from t_user_innodb where age between 30 and 49;
```

1. 辅助索引的范围查询流程和等值查询基本一致，先使用辅助索引到叶子节点检索到第一个符合条件 的索引项，然后向后遍历，直到遇到第一个不符合条件的索引项，终止 
2. 检索过程中需要将符合筛选条件的id值，依次到主键索引检索将检索的数据放入结果集中
3. 最后将查询结果返回客户端。



###### 什么是回表查询？

​	根据在辅助索引树中获取的主键id，到主键索引树检索数据的过程称为 **回表查询**

![](image/query3.png)



###### 组合索引

​	在使用索引时，组合索引是我们常用的索引类型。那组合索引是如何构建的，查找的时候又是如何 进行查找的呢？

​	表t_multiple_index，id为主键列，创建了一个联合索引idx_abc(a,b,c)，构建的B+树索引结构如图所 示。索引树中节点中的索引项按照（a，b，c）的顺序从大到小排列，先按照a列排序，a列相同时按照b 列排序，b列相同按照c列排序。在最底层的叶子节点中，如果两个索引项的a，b，c三列都相同，索引 项按照主键id排序。

```sql
CREATE TABLE `t_multiple_index` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`a` int(11) DEFAULT NULL,
`b` int(11) DEFAULT NULL,
`c` varchar(10) DEFAULT NULL,
`d` varchar(10) DEFAULT NULL,
PRIMARY KEY (`id`) USING BTREE,![query4](image/query4.png)
KEY `idx_abc` (`a`,`b`,`c`)
) ENGINE=InnoDB;
insert into t_multiple_index (a,b,c,id,d) values(1 ,1 ,4,5,'dll');
insert into t_multiple_index (a,b,c,id,d) values(1 ,5 ,4,2,'doc');
insert into t_multiple_index (a,b,c,id,d) values(5 ,3 ,6,7,'img');
insert into t_multiple_index (a,b,c,id,d) values(13,14,3,4,'xml');
insert into t_multiple_index (a,b,c,id,d) values(13,16,4,1,'txt');
insert into t_multiple_index (a,b,c,id,d) values(13,16,5,3,'pdf');
insert into t_multiple_index (a,b,c,id,d) values(13,16,5,6,'exe');
insert into t_multiple_index (a,b,c,id,d) values(14,14,14,8,'ddd');
```

![](image/query4.png)

```sql
select * from t_multiple_index where a=13 and b=16 and c=4;
```

1. 先在索引树中从根节点开始检索，将根节点加载到内存，先比较a列，a = 14，14 > 13，走左路（1 次磁盘IO） 
2.  将左子树节点加载到内存中，先比较a列，a = 13，比较b列b = 16，14 < 16，走右路，向下检索（1 次磁盘IO）
3. 达到叶节点，将节点加载到内存中从前往后遍历比较（1次磁盘IO）
   -  第一项（13, 14, 3, id = 4）：先比较a列，a = 13，比较b列b = 14，b != 16 不符合要求，丢弃
   -  第二项（13, 14, 4, id = 1）：一样的比较方式，a = 13，b = 16，c = 4 满足筛选条件。取出索引 data值即主键 id = 1，再去主键索引树中检索 id = 1 的数据放入结果集中（回表：3次磁盘IO） 
   - 第三项（13, 14, 5 ,id = 3）：a = 13，b = 16，c != 4 不符合要求，丢弃，查询结束
4. 最后得到1条符合筛选条件，将查询结果集返给客户端



**组合索引使用原则**

- **最左前缀匹配原则**
  - 组合索引的最左前缀匹配原则：使用组合索引查询时，mysql会一直向右匹配直至遇到范围查询(>、<、 between、like)就停止匹配
    - 最左前缀匹配原则和联合索引的 **索引存储结构和检索方式是有关系的**
    - 在组合索引树中，最底层的叶子节点按照第一列a列从左到右递增排列，但是b列和c列是无序的，b 列只有在a列值相等的情况下小范围内递增有序，而c列只能在a，b两列相等的情况下小范围内递增 有序
    - 所以当我们使用 where a=13 and b=16 and c=4去查询数据的时候，B+树会先比较a列来确定下一 步应该搜索的方向，往左还是往右。如果a列相同再比较b列。**但是如果查询条件没有a列，B+树就 不知道第一步应该从哪个节点查起**



**索引使用口诀**

​	全值匹配我最爱，最左前缀要遵守。 

​	带头大哥不能死，中间兄弟不能断。

 	索引列上不计算，范围之后全失效。

​	 Like百分写最右，覆盖索引不写星。

​	 不等空值还有OR，索引失效要少用。



**组合索引创建原则**

1. 频繁出现在where条件中的列，建议创建组合索引
2. 频繁出现在order by和group by语句中的列，建议按照顺序去创建组合索引。 order by a,b 需要注意索引列顺序（a,b）。如果索引的顺序是（b,a），是用不到索引的
3. 常出现在select语句中的列，也建议创建组合索引（与覆盖索引有关系）。





######  覆盖索引

​	根据在辅助索引树查询数据时，首先通过辅助索引找到主键值，然后需要再根据主键值 到主键索引中找到主键对应的数据。这个过程称为回表

​	使用辅助索引查询比基于主键索引的查询多检索了一棵索引树。**那是不是所有使用辅助索引的查询都需要回表查询呢？**

表t_multiple_index，组合索引idx_abc(a,b,c)的叶子节点中包含(a,b,c,id)四列的值，对于以下查询语句

```sql
select a from t_multiple_index where a=13 and b=16;
select a,b from t_multiple_index where a=13 and b=16;
select a,b,c from t_multiple_index where a=13 and b=16;
select a,b,c,id from t_multiple_index where a=13 and b=16;
```



​	select中列数据如果可以直接在辅助索引树上全部获取，也就是说索引树已经“覆盖”了我们的查询需求， 这时MySQL就不会白费力气的回表查询，这种现象就是覆盖索引

​	**覆盖索引是一种很常用的优化手段**



###### 索引条件下推 (ICP)

​	官方索引条件下推： Index Condition Pushdown，简称ICP。是MySQL5.6对使用索引从表中检索行的 一种优化。ICP可以减少存储引擎必须访问基表的次数以及MySQL服务器必须访问存储引擎的次数。可 用于 InnoDB 和 MyISAM 表，对于InnoDB表ICP仅用于辅助索引。 可以通过参数optimizer_switch控制ICP的开始和关闭。



```sql
-- optimizer_switch优化相关参数开关
show VARIABLES like 'optimizer_switch';

-- 关闭ICP
SET optimizer_switch = 'index_condition_pushdown=off';

-- 开启ICP
SET optimizer_switch = 'index_condition_pushdown=on';
```





​	以InnoDB的辅助索引为例，来讲解ICP的作用：MySQl在使用组合索引在检索数据时是使用最左前缀原 则来定位记录，左侧前缀之后不匹配的后缀，MySQL会怎么处理？

- 使用explain工具，查看执行计划，extra列中的“Using index condition”执行器表示使用了索引条件下推ICP
- **在MySQL 5.6之前：** 不使用ICP时，MySQL只能从索引项（13,16,4,1）开始，一个个回表查 询找到行数据，然后再在服务层过滤后，返回给客户端
- **在MySQL 5.6之后：** 在使用ICP和不使用ICP时MySQL的执行情况会有所不同
- 关闭ICP，使用explain工具，查看执行计划，extra列中的“Using where”执行器表示没有使用了索 引条件下推ICP

```sql
select * from t_multiple_index where a=13 and b>15 and c='5' and d='pdf';
```







​	MySQL的索引下推（Index Condition Pushdown, ICP）是一种查询优化技术，它允许存储引擎在读取索引时直接对索引条目执行部分过滤条件，从而减少访问实际数据行的数量。在没有使用索引下推的情况下，存储引擎会首先根据索引检索出所有匹配的主键或唯一键值，然后将这些值传递给MySQL服务器层，在服务层进行WHERE子句中其他列的过滤

索引下推的优势在于：

1. 减少IO操作：通过在存储引擎层面提前筛选掉不满足条件的数据，避免了不必要的回表操作，即减少了对主键索引或聚簇索引的查找次数
2. 提升查询性能：由于只处理满足条件的数据，因此可以加速整个查询过程，尤其是对于大数据量和高度索引化的查询来说，效果显著



###### 索引的创建原则

- 频繁出现在where 条件字段，order排序，group by分组字段 
- select 频繁查询的列，考虑是否需要创建联合索引（覆盖索引，不回表）
- 多表join关联查询，on字段两边的字段都要创建索引



###### 索引优化建议

1. **表记录很少不需创建索引 ：** 索引是要有存储的开销
2. **一个表的索引个数不能过多：**
   - 空间：浪费空间。每个索引都是一个索引树，占据大量的磁盘空间
   - 时间：更新（插入/Delete/Update）变慢。需要更新所有的索引树。太多的索引也会增加优 化器的选择时间
   - 所以索引虽然能够提高查询效率，索引并不是越多越好，应该只为需要的列创建索引
3. **频繁更新的字段不建议作为索引：** 频繁更新的字段引发频繁的页分裂和页合并，性能消耗比较高
4. **区分度低的字段，不建议建索引**
   - 比如性别，男，女；比如状态。区分度太低时，会导致扫描行数过多，再加上回表查询的消耗。 如果使用索引，比全表扫描的性能还要差。这些字段一般会用在组合索引中
   - 姓名，手机号就非常适合建索引
5. **在InnoDB存储引擎中，主键索引建议使用自增的长整型，避免使用很长的字段**
   - 主键索引树一个页节点是16K，主键字段越长，一个页可存储的数据量就会越少，比较臃肿，查询 时尤其是区间查询时磁盘IO次数会增多。辅助索引树上叶子节点存储的数据是主键值，主键值越 长，一个页可存储的数据量就会越少，查询时磁盘IO次数会增多，查询效率会降低
6.  **不建议用无序的值作为索引：** 例如身份证、UUID。更新数据时会发生频繁的页分裂，页内数据不 紧凑，浪费磁盘空间
7. **尽量创建组合索引，而不是单列索引**
   - 1个组合索引等同于多个索引效果，节省空间
   - 可以使用覆盖索引

**创建原则：** 组合索引应该把频繁用到的列、区分度高的值放在前面。频繁使用代表索引的利用率 高，区分度高代表筛选粒度大，这样做可最大限度利用索引价值，缩小筛选范围







### 常见内置参数

```sql
 # innodb的锁超时时间
 innodb_lock_wait_timeout
 
 # 查看当前的 innodb_lock_wait_timeout 值
 SHOW VARIABLES LIKE 'innodb_lock_wait_timeout';
 
 # 临时修改 innodb_lock_wait_timeout（仅对当前会话有效）
 SET SESSION innodb_lock_wait_timeout = 120;
 
 # 全局修改 innodb_lock_wait_timeout（对所有会话有效）
 SET GLOBAL innodb_lock_wait_timeout = 120;
```

