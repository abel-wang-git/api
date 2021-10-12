-- 初始化菜单
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (1, '系统管理', '/system', 1, 0, 'dashboard', 'layout/index', 0, '/user/index', '{ \"title\": \"系统设置\", \"icon\": \"dashboard\"}', NULL);
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (2, '用户管理', '/user/index', 1, 1, 'dashboard', 'views/system/user/list', 0, NULL, '{ \"title\": \"用户管理\", \"icon\": \"dashboard\"}', NULL);
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (3, '角色管理', '/role/index', 1, 1, 'dashboard', 'views/system/role/list', 0, NULL, '{ \"title\": \"角色管理\", \"icon\": \"dashboard\"}', NULL);
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (4, '菜单管理', '/menu/index', 1, 1, 'dashboard', 'views/system/menu/list', 0, NULL, '{ \"title\": \"菜单管理\", \"icon\": \"dashboard\"}', NULL);
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (10, '白名单', '/white/index', 1, 1, 'dashboard', 'views/system/whiteList/list', 0, NULL, '{ \"title\": \"白名单\", \"icon\": \"dashboard\"}', NULL);
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (11, '部门管理', '/dept/index', 1, 1, 'dashboard', 'views/system/dept/list', 0, NULL, '{ \"title\": \"部门管理\", \"icon\": \"dashboard\"}', NULL);
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (14, '日志管理', '/log/index', 1, 1, 'dashboard', 'views/system/log/index', 0, NULL, '{ \"title\": \"日志管理\", \"icon\": \"dashboard\" }', NULL);

INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (15, '地址管理', '/address/index', 1, 0, 'dashboard', 'layout/index', 0, '/address/country', '{ \"title\": \"地址管理\", \"icon\": \"dashboard\" }', NULL);
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (16, '国家管理', '/address/country', 1, 15, 'dashboard', 'views/address/country', 0, NULL, '{ \"title\": \"国家管理\", \"icon\": \"dashboard\" }', NULL);
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (17, '城市管理', '/address/city', 1, 15, 'dashboard', 'views/address/city', 0, NULL, '{ \"title\": \"城市管理\", \"icon\": \"dashboard\" }', NULL);
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (18, '省份管理', '/address/state', 1, 15, 'dashboard', 'views/address/state', 0, NULL, '{ \"title\": \"省份管理\", \"icon\": \"dashboard\" }', NULL);

INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (19, '隐藏路由', '/hidden', 1, 0, 'dashboard', 'layout/index', 1, NULL, '{ \"title\": \"隐藏\"}', NULL);
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (20, '修改国家', '/address/country/update', 1, 19, 'dashboard', 'views/address/country_detail', 1, NULL, '{ \"title\": \"修改国家\", \"icon\": \"dashboard\" }', NULL);
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (21, '修改省份', '/address/state/update', 1, 19, 'dashboard', 'views/address/state_detail', 1, NULL, '{ \"title\": \"修改省份\", \"icon\": \"dashboard\" }', NULL);
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (22, '修改城市', '/address/city/update', 1, 19, 'dashboard', 'views/address/city_detail', 1, NULL, '{ \"title\": \"修改城市\", \"icon\": \"dashboard\" }', NULL);
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (23, '行政区管理', '/address/district', 1, 19, 'dashboard', 'views/address/district', 1, NULL, '{ \"title\": \"行政区\", \"icon\": \"dashboard\" }', NULL);
INSERT INTO `menu`(`id`, `name`, `path`, `priority`, `pid`, `menu_icon`, `component`, `hidden`, `redirect`, `meta`, `alwaysShow`) VALUES (24, '修改行政区', '/address/district/update', 1, 19, 'dashboard', 'views/address/district_detail', 1, NULL, '{ \"title\": \"行政区修改\", \"icon\": \"dashboard\" }', NULL);


-- 管理员菜单权限
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 1);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 2);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 3);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 10);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 11);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 14);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 15);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 16);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 17);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 18);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 19);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 20);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 21);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 22);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 23);
INSERT INTO `role_menu`(`role_id`, `menu_id`) VALUES (22, 24);

-- 系统白名单
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/generator/code', 1);
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/generator/code/submit', 2);
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/login', 3);
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/swagger/11', 4);
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/image', 5);
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/hello', 6);
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/swagger-ui.html', 7);
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/favicon.ico', 8);
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/swagger**/**', 9);
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/webjars/**', 10);
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/v3/**', 11);
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/doc.html', 12);
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/v2/api-docs', 13);
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/image/**', 14);
INSERT INTO `sys_whitelist`(`url`, `id`) VALUES ('/media/resource/view/**', 15);