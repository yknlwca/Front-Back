DROP DATABASE IF EXISTS ssafy_board;
CREATE DATABASE ssafy_board DEFAULT CHARACTER SET utf8mb4;

USE ssafy_board;

CREATE TABLE board (
	id INT AUTO_INCREMENT,
    writer VARCHAR(20) NOT NULL,
    title VARCHAR(50) NOT NULL,
    content TEXT,
    view_cnt INT DEFAULT 0,
    reg_date TIMESTAMP DEFAULT now(),
    PRIMARY KEY(id)
);

INSERT INTO board(title, writer, content) 
VALUES ("아직은 괜찮아1","양띵균","기초 Java1"),
	   ("아직은 괜찮아2","양띵균","기초 Java2"),
       ("아직은 괜찮아3","양띵균","기초 Java3"),
       ("아직은 괜찮아4","양띵균","기초 BackEnd1"),
       ("아직은 괜찮아5","양띵균","기초 BackEnd2"),
       ("아직은 괜찮아6","양띵균","기초 BackEnd3"),
       ("아직은 괜찮아7","양띵균","Spring"),
       ("아직은 괜찮아8","양띵균","SpringBoot"),
       ("이제는 알아야해1", "양띵균", "Java 마슷허"),
       ("이제는 알아야해2", "양띵균", "Back 마슷허"),
       ("이제는 알아야해3", "양띵균", "Spring 마슷허");