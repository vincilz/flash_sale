-- ----------------------------
-- Table structure for account_freeze_tbl
-- ----------------------------
DROP TABLE IF EXISTS `account_freeze_tbl`;
CREATE TABLE `account_freeze_tbl`  (
    `id` bigint NOT NULL COMMENT '主键id',
    `request_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '请求id',
    `user_id` bigint NOT NULL DEFAULT 0 COMMENT '用户id',
    `balance` bigint NOT NULL DEFAULT 0 COMMENT '余额',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT 'TCC状态',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '账户冻结表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account_freeze_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for account_tbl
-- ----------------------------
DROP TABLE IF EXISTS `account_tbl`;
CREATE TABLE `account_tbl`  (
    `id` bigint NOT NULL COMMENT '主键id',
    `balance` bigint NOT NULL DEFAULT 0 COMMENT '余额',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '账户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account_tbl
-- ----------------------------
INSERT INTO `account_tbl` VALUES (2103508996, 13900);

-- ----------------------------
-- Table structure for order_freeze_tbl
-- ----------------------------
DROP TABLE IF EXISTS `order_freeze_tbl`;
CREATE TABLE `order_freeze_tbl`  (
    `id` bigint NOT NULL COMMENT '主键id',
    `request_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '请求id',
    `order_id` bigint NOT NULL DEFAULT 0 COMMENT '订单id',
    `user_id` bigint NOT NULL DEFAULT 0 COMMENT '用户id',
    `product_id` bigint NOT NULL DEFAULT 0 COMMENT '商品数量',
    `quantity` int NOT NULL DEFAULT 0 COMMENT '下单数量',
    `total_price` bigint NOT NULL DEFAULT 0 COMMENT '总价格',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT 'TCC状态',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单冻结表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_freeze_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for order_tbl
-- ----------------------------
DROP TABLE IF EXISTS `order_tbl`;
CREATE TABLE `order_tbl`  (
    `id` bigint NOT NULL COMMENT '主键id',
    `user_id` bigint NOT NULL DEFAULT 0 COMMENT '用户id',
    `product_id` bigint NOT NULL DEFAULT 0 COMMENT '商品id',
    `quantity` int NOT NULL DEFAULT 0 COMMENT '下单数量',
    `total_price` bigint NOT NULL DEFAULT 0 COMMENT '总价格',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for storage_freeze_tbl
-- ----------------------------
DROP TABLE IF EXISTS `storage_freeze_tbl`;
CREATE TABLE `storage_freeze_tbl`  (
    `id` bigint NOT NULL COMMENT '主键id',
    `request_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '请求id',
    `product_id` bigint NOT NULL COMMENT '商品id',
    `quantity` int NOT NULL DEFAULT 0 COMMENT '库存',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT 'TCC状态',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '库存冻结表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of storage_freeze_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for storage_tbl
-- ----------------------------
DROP TABLE IF EXISTS `storage_tbl`;
CREATE TABLE `storage_tbl`  (
    `id` bigint NOT NULL COMMENT '主键id',
    `quantity` int NOT NULL DEFAULT 0 COMMENT '库存',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '库存表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of storage_tbl
-- ----------------------------
INSERT INTO `storage_tbl` VALUES (2103508995, 960);

SET FOREIGN_KEY_CHECKS = 1;
