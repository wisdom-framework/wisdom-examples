This is a simple todo app built using the Wisdom Framework.

### todo-api
- This bundle holds the interface/contract for implementing the todo app. 
- The consumer would never know the details of the concrete implementation.
- The concrete implementation of the API can be changed at runtime. 

### todo-derby-provider
- This bundle provides the concrete implementation of the todo-api interface/contract.
- It uses an in-memory derby db to persist all the todo data.

### todo-inmemory-provider
- This bundle provides the concrete implementation of the todo-api interface/contract.
- It uses an in-memory ConcurrentHashMap to persist all the todo data.

### todo-web
- This bundle provides a web view of doing CRUD of Todo Items. 
- Based on the provider bundle set as dependency the Todo Items are either persisted across sessions or terminated on the end of every session.
