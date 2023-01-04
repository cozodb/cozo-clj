# cozo-clj

[![java](https://img.shields.io/maven-central/v/io.github.cozodb/cozo-clj)](https://mvnrepository.com/artifact/io.github.cozodb/cozo-clj)

[Cozo 数据库](https://www.cozodb.org) 的 Clojure 库。

本文叙述的是如何安装设置库本身。有关如何使用 CozoDB（CozoScript）的信息，见 [文档](https://docs.cozodb.org/zh_CN/latest/index.html) 。

## 使用方法

```clojure
(in-ns 'cozo-clj.core)

(def db (open-db))

(println (query db "?[] <- [[1,2,3]]"))

(close db)
```

更多信息见库内置的文档。

## 依赖

本库通过 `io.github.cozodb/cozo-lib-java` 库来调用原生 Cozo 库。

## 由于网络原因无法下载原生库，怎么办？

和在 [Java 中的解决办法](https://gitee.com/cozodb/cozo-lib-java#%E7%94%B1%E4%BA%8E%E7%BD%91%E7%BB%9C%E5%8E%9F%E5%9B%A0%E6%97%A0%E6%B3%95%E4%B8%8B%E8%BD%BD%E5%8E%9F%E7%94%9F%E5%BA%93%E6%80%8E%E4%B9%88%E5%8A%9E) 一样。