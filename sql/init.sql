-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS account (
  `id` integer NOT NULL AUTO_INCREMENT COMMENT 'ユーザーID',
  `email` varchar(255) NOT NULL COMMENT 'メールアドレス',
  `password` varchar(255) NOT NULL COMMENT 'パスワード',
  PRIMARY KEY (`id`)
)
COMMENT = 'ユーザー';

-- -----------------------------------------------------
-- Table `dish`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS dish (
  `id` integer NOT NULL COMMENT '料理ID',
  `user_id` integer NOT NULL COMMENT 'ユーザーID',
  `name` varchar(255) NOT NULL COMMENT '料理名',
  `preparation_time` integer NOT NULL COMMENT '調理時間',
  `cost` integer NOT NULL COMMENT '費用',
  `image_url` varchar(255) NOT NULL COMMENT '画像URL',
  `material` varchar(255) NOT NULL COMMENT '材料',
  `recipe` varchar(255) NOT NULL COMMENT '手順',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新規作成日時',
  PRIMARY KEY (`id`)
)
COMMENT = '料理';

-- -----------------------------------------------------
-- Table `dish`
-- -----------------------------------------------------
ALTER TABLE dish
ADD FOREIGN KEY (`user_id`) REFERENCES account(`id`) ON DELETE RESTRICT;



insert into account (id, email, password)
values
(1, 'test@dokkyo.ac.ne.jp','$2a$08$FyQG8hycemABdjNcOwxkWOlDb5qsbgRYr0Snw/cOAK12avUDbNFHi');