# Never STORE an Activity instance or View that references any activity on ViewModel
# It will cause dangling pointer, as if that activity gets destroyed, ViewModel will till store that reference.
# But it requires to pass a context to our repository to instantiate database instance. So we provide
  Application to it's constructor.