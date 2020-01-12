[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

# springboot2-jpa-api-for-luban

* #!zh: 为[鲁班H5](https://github.com/ly525/luban-h5) 提供 由 [Spring Boot](https://spring.io/projects/spring-boot) 驱动的后端 API
* #!zh: 现在仍然在完善中，非常欢迎 PR，如果您想参与贡献，可以直接 Pull Request。也可以和作者直接联系, [联系方式](https://github.com/ly525/luban-h5#%E4%BA%A4%E6%B5%81%E7%BE%A4)

* #!en: Demo API for [Luban-H5-Editor-Module](https://github.com/ly525/luban-h5) powered by [Spring Boot + JPA](https://spring.io/projects/spring-boot)
* #!en It is still working in progress, so pr is welcome!(now it's just a demo)

#### 相关文档
* [说明文档(WIP/完善中)](https://www.yuque.com/xpm1xa/rgf7kz/xkm4aq)


### TODO
> pr is welcome!

- [x] Get Work By Id
- [x] Get All Works
- [x] Update Work
- [x] Create Work
- [x] Delete Work
- [ ] Upload Work Cover
- [ ] Cors Proxy
- [x] Set Work as Template
- [ ] create Work based on Template


### Development
##### 后端
1. 使用 `init.sql` 初始化数据(不含建库语句)
2. 修改 `src/main/resources/application-dev.example.yml`  中的 mysql 相关配置
3. 修改 `src/main/resources/application-prod.example.yml` 中的 mysql 相关配置
4. `git clone https://github.com/luban-h5/springboot2-jpa-api-for-luban` , 启动 Spring Boot 项目


##### 前端
> 预备知识
> * [Node、yarn 安装教程](https://github.com/ly525/luban-h5/blob/dev/docs/zh/getting-started/quick-start.md#nodeyarnnpm%E5%AE%89%E8%A3%85)
> * 请使用 yarn 安装依赖，而非 npm，原因参见 [#92](https://github.com/ly525/luban-h5/issues/92) 和 [#101](https://github.com/ly525/luban-h5/issues/101) 
> * 安装前端项目依赖，[前端开发文档](https://github.com/ly525/luban-h5/blob/dev/docs/zh/getting-started/quick-start.md)：


```bash
# 重新打开一个 terminal 
git clone https://github.com/ly525/luban-h5
cd luban-h5/front-end/h5

# 安装前端依赖
yarn install 
# #!en modify `target` in `vue.config.js`
# #!zh 修改 vue.config.js 中的 target 变量，比如：const target = 'http://127.0.0.1:8888'，
# 其中：
    # 8888 为 springboot2-jpa-api-for-luban 提供服务的端口
    # 127.0.0.1 是本地开发的IP 或者 内网 IP 都是可以的

# 修改完毕之后，运行下面的命令，即可启动前端服务进行联调 
# 注意是 serve 不是 server!
yarn serve 

```

联调开始
