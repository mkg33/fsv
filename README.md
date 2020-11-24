# fsv

M; OK, I simply don't get the delete() and delMin() testing functions - I keep getting the 'sibling null exception' when I test it with for loops.
So I just tweaked it and created 3 elements instead. The coverage is above 80%, so that's alright.

Let me know if you're happy with the final result and then we can submit the project. Sorry, I can't improve the delete() and delMin() functions, wasted too much time on it already.

Also, the catch blocks are unreachable (in my tests) because if I allow 'failing arguments', the exception is caught but the console output says that the exception was caught but further checks are automatically terminated. This is stupid. Or pehaps I am. I think the latter.

Incidentally, your code also has unreachable elements (in particular, the catch blocks). Hmm and also the for loop in the toString() function. I'm looking into it now.

OK, I fixed it. The culprit was in the second for loop (tmp replace all - the assertion mustn't be inside the loop).
