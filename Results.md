# Experimental results

## Findings
We noticed that the single-threaded approach tended to be much slower for any number of connections. We'd get around 10 seconds of processing time for a batch of 20 clients connecting to the server and sending the Threaded-server picture that came with the repo. With our multi-threaded approach that same number of clients was process in 4 seconds. That many clients did put significantly more strain on the CPU however (~%60-70% more). Testing CPU was Ryzen 5 3600: 6 cores 12 threads. We only tried a single variation where every client gets their own thread.
