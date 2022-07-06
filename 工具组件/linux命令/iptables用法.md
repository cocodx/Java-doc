查看iptables的规则：  

iptables -nL --line-number

删除iptables的规则：  

iptables -D INPUT 16

添加iptables的规则：

iptables -A OUTPUT -p tcp --dport 3308 -j ACCEPT  
iptables -A INPUT -p tcp --dport 3308 -j ACCEPT

