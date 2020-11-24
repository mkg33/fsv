# fsv

OK, I simply don't get the delete() and delMin() testing functions - I keep getting the 'sibling null exception' when I test it with for loops.
So I just tweaked it and created 3 elements instead. The coverage is above 80%, so that's alright.

Let me know if you're happy with the final result and then we can submit the project. Sorry, I can't improve the delete() and delMin() functions, wasted too much time on it already.

Also, the catch blocks are unreachable (in my tests) because if I allow 'failing arguments', the exception is caught but the console output says that the exceptions was caught but further checks are automatically stopped. This is stupid. Or pehaps I am :D. I think the latter.
