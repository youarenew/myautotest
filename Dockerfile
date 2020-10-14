FROM openjdk
# 制作人
MAINTAINER masonsjz
# 创建一个目录存放jar包和配置
#RUN mkdir -p /opt/myapp
COPY  ./target/myautotest-0.0.1.jar /usr/app/app.jar
# 工作目录
WORKDIR /opt/app
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
RUN bash -c 'touch app.jar'

ENV JAVA_OPTS="\
-server \
-Xmx512m \
-Xms512m \
-verbose:gc \
-XX:+PrintGCDetails \
-XX:+PrintGCDateStamps"
ENTRYPOINT java ${JAVA_OPTS} -jar app.jar

#启动容器执行命令，指定外部配置文件
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/myapp/app.jar"]
#ENTRYPOINT ["java","-jar","/opt/myapp/app.jar","--spring.config.location=/opt/config/application_bak"]