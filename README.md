# spring-boot-ignite-hello-world
A Hello World project with spring-boot and ignite.



####server:
_server_ profile creates all the necessary nodes to work with, plus one client node.
It is a stand-alone application you can test all the features of ignite without creating new instance(s).

####client:
_client_ profile does not create any nodes except a client node.

#####standalone web console:
You can use docker-compose file to initiate a container that
contains apacheignite/web-console-standalone,
 however to be able to use web-console further configurations are required:
 - You need to register and login to your web-console
 - You need to start an **ignite-web-agent**

PS: For deeper info, article [_**Apache Ignite Cluster together with Spring Boot**_](https://piotrminkowski.wordpress.com/2018/04/04/apache-ignite-cluster-together-with-spring-boot/) of **Piotr Mińkowski** is great!
  