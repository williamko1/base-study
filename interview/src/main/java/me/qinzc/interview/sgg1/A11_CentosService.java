package me.qinzc.interview.sgg1;

/**
 * desc :
 *              centos6                            centos7
 * 服务启动       service 服务名 start/stop            systemctl start/stop 服务名
 * 服务自启动     chkconfig --level 3/5 服务名 on/off  systemctl enable/disable 服务名
 * 查看服务       /etc/init.d/服务名                   systemctl list-unit-files
 *              chkconfig --list|grep 服务名          systemctl --type service
 *
 * @author : Zane Qin
 * createTime : 4:57 2023/3/15
 */
public class A11_CentosService {
}
