[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

# spring-boot-api-for-editor

* #!zh: 为[鲁班H5](https://github.com/ly525/luban-h5) 提供 由 [Spring Boot](https://spring.io/projects/spring-boot) 驱动的后端 API
* #!zh: 现在仍然在完善中，非常欢迎 PR，如果您想参与贡献，可以直接 Pull Request。也可以和作者直接联系, [联系方式](https://github.com/ly525/luban-h5#%E4%BA%A4%E6%B5%81%E7%BE%A4)

---

* #!en: Demo API for [Luban-H5-Editor-Module](https://github.com/ly525/luban-h5) powered by [Spring Boot](https://spring.io/projects/spring-boot)
* #!en It is still working is progress, so pr is welcome!(now it's just a demo)

#### 相关文档
* [说明文档(WIP/完善中)](https://www.yuque.com/xpm1xa/rgf7kz/xkm4aq)


### TODO
> pr is welcome!

- [x] Get Work By Id
- [x] Get All Works
- [x] Update Work
- [x] Create Work
- [ ] Delete Work
- [ ] Upload Work Cover
- [ ] Cors Proxy
- [ ] Set Work as Template
- [ ] create Work based on Template


### Development
>  #!zh 前后端联调开发

```bash
git clone https://github.com/ly525/luban-h5
cd luban-h5/front-end/h5
yarn install # 安装前端项目依赖，[前端开发文档](https://github.com/ly525/luban-h5/blob/dev/docs/zh/getting-started/quick-start.md)：
# #!en modify `target` in `vue.config.js`
# #!zh 修改 vue.config.js 中的 target 变量，比如：const target = 'http://127.0.0.1:8888'，
# #!zh 8888 为 spring-boot-api-for-editor 提供服务的端口, 修改完毕之后，运行下面的命令，即可启动前端服务进行联调 
yarn serve # 是 serve 不是 server!

# 另外开一个terminal
# open another terminal
https://github.com/luban-h5/spring-boot-api-for-editor

# 启动 Spring Boot 项目，联调开始
```
