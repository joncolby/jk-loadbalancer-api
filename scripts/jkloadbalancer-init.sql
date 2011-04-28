create database jkloadbalancer;
grant all privileges on jkloadbalancer.* to 'jkloadbalancer'@'localhost' identified by 'jkloadbalancer';
grant all privileges on jkloadbalancer.* to 'jkloadbalancer'@'10.%' identified by 'jkloadbalancer';
