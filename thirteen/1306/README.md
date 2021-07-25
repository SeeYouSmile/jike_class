分别启动3个子项目：server、producer、consumer

producer接口：
发送消息
url:/producer/send
params:
{
	"topic":"topic",//topic名称
    "body":{//消息内容
        "id":"1",
        "content":"content"
    }
}

consumer接口：
获取消息
url:/consumer/poll
params:
{
    "topic":"topic",//topic名称
    "consumer":"consumer",//消费者名称
    "n":1,//获取消息数量
    "autoAck":false//是否自动确认消息消费
}

url:/consumer/ack
确认消费
params:
{
    "topic":"topic",//topic名称
    "consumer":"consumer"//消费者名称
}

三个接口的返回：
{
    "status": 0,//0为成功，1为失败
    "bodies": [//除了发送消息接口，其它两个接口都返回null
        {
            "id": "1",
            "content": "content"
        }
    ]
}