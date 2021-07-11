Spring framework provides an abstract layer on top of different underlying transaction management APIs.

PROPAGATION TYPES:
1. Mandatory
Indicates that the target method requires an active tx to be running. If atxis not going on, it will fail by throwing an exception.
2. Nested
Executes within a nested transaction if a current transaction exists.
3. Never
Indicates that the target method will raise an exception if executed in a transactional process.
This option is mostly not used in projects.
4. Not_Supported
Indicates that the target method doesn’t require the transaction context to be propagated.
Mostly those methods which run in a transaction but perform in-memory operations are the best candidates for this option.
5. Required
Indicates that the target method cannot run without an active tx. If atxhas already been started before the invocation of this method, then it will continue in the same tx or a newtxwould begin soon as this method is called. 
6. Requires_new
Indicates that a newtxhas to start every time the target method is called. If already atxis going on, it will be suspended before starting a new one.
7. Supports
Indicates that the target method can execute irrespective of atx. If atxis running, it will participate in the same tx. If executed without a tx it will still execute if no errors.
Methods which fetch data are the best candidates for this option.

An important aspect of transaction management is defining the right transaction boundary, when should a transaction start, when should it end, when data should be committed in DB and when it should be rolled back (in the case of exception).

In proxy mode (which is the default), only 'external' method calls coming in through the proxy will be intercepted. This means that 'self-invocation', i.e. a method within the target object calling some other method of the target object, won't lead to an actual transaction at runtime even if the invoked method is marked with @Transactional.

Consider another example where your Service layer may call two different DAO methods to perform DB operations. If your first DAO operation failed, then the other two may be still passed and you will end up inconsistent DB state. Annotating a Service layer can save you from such situations.

We will go over on how does @Transactional really work under the hood. 

You can handle transactions programmatically as below:
try { 
    utx.begin(); 
    businessLogic();
    utx.commit(); 
} catch(Exception ex) { 
    utx.rollback(); 
    throw ex; 
} 

This way of managing transactions makes the scope of the transaction very clear in the code, but it has several disadvantages:
* It's repetitive and error prone. Any error can have high impact.
* Errors are hard to debug and reproduce.
* This decreases the readability of the code base.
* What if the method calls another transactional method?

With Spring @Transactional, the above code gets reduced to simply this:

@Transactional
public void businessLogic() {
... use entity manager inside a transaction ...
}

Transaction progagation means getting the methods in a methods chain under the transaction.

By using @Transactional, many important aspects such as transaction propagation are handled automatically. In this case if another transactional method is called by businessLogic(), that method will have the option of joining the ongoing transaction.

One potential downside is that this powerful mechanism hides what is going on under the hood, making it hard to debug when things don't work.

Read this again: https://dzone.com/articles/how-does-spring-transactional

