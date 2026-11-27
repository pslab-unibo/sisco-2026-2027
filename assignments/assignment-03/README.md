Concurrent Systems a.y. 2026-2027 - ISI LM UNIBO - Cesena Campus

# Assignment #03

The assignment concerns 3 points.

1) Implement a library called `MyLib` providing an **asynchronous  method** called `getPFDInventory`. The method has a single parameter - a directory D - and it must returns (asynchronously) an inventory, containing information about all PDF files found in the directory D (and in subdirectories, recursively), including - for each PDF file - its name, location, and number of pages. To work with PDF files, the [Apache PDFBox library](https://pdfbox.apache.org/) can be used. A simple example of usage is provided in the repo.  

    The library and the example program must be developed using two different programming approaches: 
      1. **Asynchronous programming** based on event-loops; 
      2. **Virtual Threads**.
    
    In both cases, the library should be accompained by a simple main program, exemplifying its usage. 

    **Important remark**: each version should be designed focussing exclusively on the programming discipline that characterises each approach. That is: don't generalise or reuse code when this would negatively impact on the design and programming discipline promoted by each individual approach.

2) Think about an *interactive* extension of the library described in previous point, which should provide the possibility to dynamically get updates about the inventory. The library should be accompained by a minimal GUI, useful to show the features of the extension.

    This point must be designed and developed using **reactive programming**, in particulare the ReactiveX framework (e.g. RxJava).

    *Optional* (mandatory for 30 cum laude): include the possibility to stop the process.

3) A problem about message passing - synchronous and asynchronous. TO BE ANNOUNCED.

### The deliverable

The deliverable must be a zipped folder `Assignment-03`, to be submitted on the course web site, including:  
- `src` directory with sources
- `doc` directory with a short report in PDF (`report.pdf`). The report should include:
	- A brief analsysis of the problem, focusing in particular aspects that are relevant from a  concurrent point of view.
	- A brief description of the strategy adopted

