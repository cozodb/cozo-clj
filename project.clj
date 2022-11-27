(defproject io.github.cozodb/cozo-clj "0.2.1"
  :description "CozoDb Clojure bindings"
  :url "http://github.com/cozodb/cozo"
  :repositories {"snapshots" {:url "https://s01.oss.sonatype.org/content/repositories/snapshots"}}
  :deploy-repositories [["releases" {:url      "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2"
                                     :username [:gpg :env/sonatype_username]
                                     :password [:gpg :env/sonatype_password]}
                         "snapshots" {:url      "https://s01.oss.sonatype.org/content/repositories/snapshots"
                                      :username [:gpg :env/sonatype_username]
                                      :password [:gpg :env/sonatype_password]}]]
  :classifiers [["sources" {:source-paths      ^:replace []
                            :java-source-paths ^:replace ["src/java"]
                            :resource-paths    ^:replace []}]
                ["javadoc" {:source-paths      ^:replace []
                            :java-source-paths ^:replace []
                            :resource-paths    ^:replace ["javadoc"]}]]
  :pom-addition ([:developers
                  [:developer
                   [:id "zh217"]
                   [:name "Ziyang Hu"]]])
  :scm {:name "git" :url "https://github.com/cozodb/cozo-clj"}
  :license {:name "MIT/Apache-2.0/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.json "2.4.0"]
                 [io.github.cozodb/cozo_java "0.2.1"]]
  :plugins [[lein-codox "0.10.8"]]
  :repl-options {:init-ns cozo-clj.core})
