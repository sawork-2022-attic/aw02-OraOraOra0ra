# POS in Shell

The demo shows a simple POS system with command line interface. Currently it implements three commands which you can see using the `help` command.

```shell
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.7)
 
shell:>help
AVAILABLE COMMANDS

Built-In Commands
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.
        history: Display or save the history of previously run commands
        script: Read and execute commands from a file.
        stacktrace: Display the full stacktrace of the last error.

Pos Command
        a: Add a Product to Cart
        n: New Cart
        p: List Products
```

Everytime a customer come to make a purchase, use `n` to create a new cart and then use `a ${productid} ${amount}` to add a product to the cart.

Please make the POS system robust and fully functional by implementing more commands, for instance, print/empty/modify cart.

Implementing a PosDB with real database is very much welcome. 

Please elaborate your understanding in layered systems via this homework in your README.md.



------

在Java项目开发中，使用分层的方法可以实现高内聚（分层简化系统设计，不同层专注做某一模块）、低耦合（层与层之间只需通过接口交互）的项目结构，极大有利于项目开发。

三层结构分别为表现层（展示界面）、业务逻辑层（针对具体问题进行处理，也是对数据层的操作）、数据访问层（直接操作数据库，针对数据的增删改查）。

在此次作业中，PosCommand对应表示层，展示命令行界面与相对于信息；PosService(Imp)对应业务处理层，负责接受表示层传来的命令进行处理，同时产生修改Cart数据的请求发往数据访问层，主要业务都在此层实现；PosInMemoryDB对应数据访问层，通过接受命令提供相应的数据服务。

高内聚低耦合是分层结构最显著的特点，其更加明确的结构有利于软件工程标准化开发，同时也有利于后期的维护工作。但是相应也会伴随着一些无法避免的缺点，例如影响系统性能。例如POS系统如果不采用分层式结构，当接收到命令时便可以直接访问数据，但现在确实需要通过中间层来完成。

总体来说我觉得这是一种相当优秀的开发设计方法，期待本学期继续学习。