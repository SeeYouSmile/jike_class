# 第2周作业


## 作业内容

> Week02 作业题目（周四）：

1.使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。

串行
在没有设置初始大小时，每当发生fullGC后会扩大堆内存大小。平均耗时24.1ms，最大耗时90.0ms
-Xmx1g -XX:+PrintGCDetails -XX:+UseSerialGC
[GC (Allocation Failure) [DefNew: 51904K->6463K(58368K), 0.0088828 secs] 51904K->17158K(188096K), 0.0089220 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [DefNew: 58367K->6463K(58368K), 0.0126862 secs] 69062K->38659K(188096K), 0.0127188 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [DefNew: 57919K->6461K(58368K), 0.0110467 secs] 90115K->61015K(188096K), 0.0110759 secs] [Times: user=0.00 sys=0.01, real=0.01 secs] 
[GC (Allocation Failure) [DefNew: 58114K->6462K(58368K), 0.0088581 secs] 112667K->78093K(188096K), 0.0088868 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [DefNew: 58214K->6457K(58368K), 0.0087486 secs] 129845K->93912K(188096K), 0.0087762 secs] [Times: user=0.00 sys=0.02, real=0.01 secs] 
[GC (Allocation Failure) [DefNew: 58200K->6457K(58368K), 0.0081124 secs] 145656K->110022K(188096K), 0.0081407 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [DefNew: 58139K->6462K(58368K), 0.0085542 secs] 161703K->126468K(188096K), 0.0085888 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [DefNew: 58295K->6463K(58368K), 0.0095315 secs][Tenured: 139031K->130416K(139152K), 0.0217780 secs] 178301K->130416K(197520K), [Metaspace: 3406K->3406K(1056768K)], 0.0316893 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
[GC (Allocation Failure) [DefNew: 87029K->10813K(97920K), 0.0099954 secs] 217446K->155367K(315284K), 0.0100260 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [DefNew: 97648K->10812K(97920K), 0.0153535 secs] 242201K->179994K(315284K), 0.0153856 secs] [Times: user=0.00 sys=0.00, real=0.02 secs] 
[GC (Allocation Failure) [DefNew: 97747K->10815K(97920K), 0.0154666 secs] 266929K->208508K(315284K), 0.0155151 secs] [Times: user=0.00 sys=0.01, real=0.02 secs] 
[GC (Allocation Failure) [DefNew: 97919K->10813K(97920K), 0.0141621 secs][Tenured: 224298K->188827K(224300K), 0.0358192 secs] 295612K->188827K(322220K), [Metaspace: 3407K->3407K(1056768K)], 0.0503188 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
[GC (Allocation Failure) [DefNew: 126016K->15679K(141696K), 0.0107884 secs] 314843K->224647K(456412K), 0.0108163 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [DefNew: 141577K->15679K(141696K), 0.0218787 secs] 350545K->264438K(456412K), 0.0219118 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
[GC (Allocation Failure) [DefNew: 141695K->15673K(141696K), 0.0212166 secs] 390454K->307141K(456412K), 0.0212442 secs] [Times: user=0.00 sys=0.02, real=0.02 secs] 
[GC (Allocation Failure) [DefNew: 141689K->15677K(141696K), 0.0195080 secs][Tenured: 328475K->261425K(328580K), 0.0451306 secs] 433157K->261425K(470276K), [Metaspace: 3407K->3407K(1056768K)], 0.0650474 secs] [Times: user=0.03 sys=0.03, real=0.07 secs] 
[GC (Allocation Failure) [DefNew: 174400K->21759K(196160K), 0.0149635 secs] 435825K->318327K(631872K), 0.0149914 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
[GC (Allocation Failure) [DefNew: 196159K->21758K(196160K), 0.0248751 secs] 492727K->372610K(631872K), 0.0249321 secs] [Times: user=0.01 sys=0.02, real=0.03 secs] 
[GC (Allocation Failure) [DefNew: 196063K->21759K(196160K), 0.0284621 secs] 546915K->428110K(631872K), 0.0284959 secs] [Times: user=0.02 sys=0.01, real=0.03 secs] 
[GC (Allocation Failure) [DefNew: 196076K->21759K(196160K), 0.0267750 secs][Tenured: 459118K->309512K(459184K), 0.0600517 secs] 602427K->309512K(655344K), [Metaspace: 3411K->3411K(1056768K)], 0.0870722 secs] [Times: user=0.06 sys=0.03, real=0.09 secs] 
[GC (Allocation Failure) [DefNew: 206400K->25792K(232192K), 0.0190505 secs] 515912K->374372K(748048K), 0.0190830 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
[GC (Allocation Failure) [DefNew: 232192K->25792K(232192K), 0.0207493 secs] 580772K->436235K(748048K), 0.0207795 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
执行结束!共生成对象次数:9689
Heap
 def new generation   total 232192K, used 208940K [0x00000000c0000000, 0x00000000cfbf0000, 0x00000000d5550000)
  eden space 206400K,  88% used [0x00000000c0000000, 0x00000000cb2db3d8, 0x00000000cc990000)
  from space 25792K, 100% used [0x00000000cc990000, 0x00000000ce2c0000, 0x00000000ce2c0000)
  to   space 25792K,   0% used [0x00000000ce2c0000, 0x00000000ce2c0000, 0x00000000cfbf0000)
 tenured generation   total 515856K, used 410443K [0x00000000d5550000, 0x00000000f4d14000, 0x0000000100000000)
   the space 515856K,  79% used [0x00000000d5550000, 0x00000000ee622f48, 0x00000000ee623000, 0x00000000f4d14000)
 Metaspace       used 3741K, capacity 4540K, committed 4864K, reserved 1056768K
  class space    used 415K, capacity 428K, committed 512K, reserved 1048576K
  
并行CMS
设置了初始堆大小和最大一样，没有设置自适应参数，没有观察到堆大小的变化。平均耗时23.8ms，最大耗时40.0ms
-Xmx1g -Xms1g -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC
[GC (Allocation Failure) [ParNew: 272640K->34047K(306688K), 0.0175922 secs] 272640K->82833K(1014528K), 0.0176588 secs] [Times: user=0.02 sys=0.05, real=0.02 secs] 
[GC (Allocation Failure) [ParNew: 306687K->34048K(306688K), 0.0256648 secs] 355473K->156897K(1014528K), 0.0256999 secs] [Times: user=0.05 sys=0.06, real=0.03 secs] 
[GC (Allocation Failure) [ParNew: 306688K->34048K(306688K), 0.0367809 secs] 429537K->234880K(1014528K), 0.0368144 secs] [Times: user=0.09 sys=0.00, real=0.04 secs] 
[GC (Allocation Failure) [ParNew: 306688K->34048K(306688K), 0.0373487 secs] 507520K->315335K(1014528K), 0.0373946 secs] [Times: user=0.11 sys=0.01, real=0.04 secs] 
[GC (Allocation Failure) [ParNew: 306688K->34048K(306688K), 0.0377112 secs] 587975K->395430K(1014528K), 0.0377434 secs] [Times: user=0.06 sys=0.03, real=0.04 secs] 
[GC (CMS Initial Mark) [1 CMS-initial-mark: 361382K(707840K)] 396151K(1014528K), 0.0001625 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.008/0.008 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.03 sys=0.00, real=0.00 secs] 
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 306688K->34048K(306688K), 0.0375120 secs] 668070K->476459K(1014528K), 0.0375437 secs] [Times: user=0.06 sys=0.03, real=0.04 secs] 
[GC (Allocation Failure) [ParNew: 306688K->34048K(306688K), 0.0385776 secs] 749099K->566268K(1014528K), 0.0386083 secs] [Times: user=0.08 sys=0.02, real=0.04 secs] 
[GC (Allocation Failure) [ParNew: 306688K->34048K(306688K), 0.0324968 secs] 838908K->640370K(1014528K), 0.0325292 secs] [Times: user=0.05 sys=0.05, real=0.03 secs] 
[GC (Allocation Failure) [ParNew: 306688K->34048K(306688K), 0.0374810 secs] 913010K->727148K(1014528K), 0.0375125 secs] [Times: user=0.13 sys=0.02, real=0.04 secs] 
[CMS-concurrent-abortable-preclean: 0.008/0.313 secs] [Times: user=0.47 sys=0.11, real=0.31 secs] 
[GC (CMS Final Remark) [YG occupancy: 39613 K (306688 K)][Rescan (parallel) , 0.0005649 secs][weak refs processing, 0.0000070 secs][class unloading, 0.0003307 secs][scrub symbol table, 0.0004542 secs][scrub string table, 0.0001189 secs][1 CMS-remark: 693100K(707840K)] 732713K(1014528K), 0.0015854 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [ParNew: 306688K->34048K(306688K), 0.0187323 secs] 874765K->677425K(1014528K), 0.0187776 secs] [Times: user=0.06 sys=0.00, real=0.02 secs] 
[GC (CMS Initial Mark) [1 CMS-initial-mark: 643377K(707840K)] 677931K(1014528K), 0.0001943 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.004/0.004 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-abortable-preclean-start]
[CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (CMS Final Remark) [YG occupancy: 70097 K (306688 K)][Rescan (parallel) , 0.0005998 secs][weak refs processing, 0.0000065 secs][class unloading, 0.0004144 secs][scrub symbol table, 0.0004329 secs][scrub string table, 0.0001520 secs][1 CMS-remark: 643377K(707840K)] 713475K(1014528K), 0.0017064 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [ParNew: 306688K->34048K(306688K), 0.0243311 secs] 623238K->437645K(1014528K), 0.0243960 secs] [Times: user=0.09 sys=0.00, real=0.02 secs] 
[GC (CMS Initial Mark) [1 CMS-initial-mark: 403597K(707840K)] 443127K(1014528K), 0.0001448 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.005/0.005 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 306688K->34048K(306688K), 0.0206397 secs] 710285K->521456K(1014528K), 0.0206718 secs] [Times: user=0.03 sys=0.00, real=0.02 secs] 
执行结束!共生成对象次数:12209
Heap
 par new generation   total 306688K, used 45582K [0x00000000c0000000, 0x00000000d4cc0000, 0x00000000d4cc0000)
  eden space 272640K,   4% used [0x00000000c0000000, 0x00000000c0b43a80, 0x00000000d0a40000)
  from space 34048K, 100% used [0x00000000d0a40000, 0x00000000d2b80000, 0x00000000d2b80000)
  to   space 34048K,   0% used [0x00000000d2b80000, 0x00000000d2b80000, 0x00000000d4cc0000)
 concurrent mark-sweep generation total 707840K, used 487408K [0x00000000d4cc0000, 0x0000000100000000, 0x0000000100000000)
 Metaspace       used 3753K, capacity 4540K, committed 4864K, reserved 1056768K
  class space    used 415K, capacity 428K, committed 512K, reserved 1048576K

并行G1
-Xmx1g -Xms1g -XX:+PrintGCDetails -XX:+UseG1GC
gc日志太多就不贴了
执行结束!共生成对象次数:10609
Heap
 garbage-first heap   total 1048576K, used 586503K [0x00000000c0000000, 0x0000000100000000, 0x0000000100000000)
  region size 1024K, 51 young (52224K), 9 survivors (9216K)
 Metaspace       used 3740K, capacity 4540K, committed 4864K, reserved 1056768K
  class space    used 415K, capacity 428K, committed 512K, reserved 1048576K
  
平均耗时3.64ms，最大耗时15.0ms
-Xmx1g -Xms1g -XX:+PrintGC -XX:+UseG1GC
[GC pause (G1 Evacuation Pause) (young) 62M->26M(1024M), 0.0038686 secs]
[GC pause (G1 Evacuation Pause) (young) 82M->49M(1024M), 0.0039495 secs]
[GC pause (G1 Evacuation Pause) (young) 110M->77M(1024M), 0.0036398 secs]
[GC pause (G1 Evacuation Pause) (young) 130M->100M(1024M), 0.0038832 secs]
[GC pause (G1 Evacuation Pause) (young) 181M->141M(1024M), 0.0054457 secs]
[GC pause (G1 Evacuation Pause) (young) 232M->188M(1024M), 0.0063029 secs]
[GC pause (G1 Evacuation Pause) (young) 283M->224M(1024M), 0.0062335 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark)-- 820M->671M(1024M), 0.0149734 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001206 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0050186 secs]
[GC remark, 0.0008524 secs]
[GC cleanup 697M->539M(1024M), 0.0006168 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0002095 secs]
[GC pause (G1 Evacuation Pause) (young) 569M->535M(1024M), 0.0132333 secs]
[GC pause (G1 Evacuation Pause) (mixed) 581M->458M(1024M), 0.0056712 secs]
[GC pause (G1 Evacuation Pause) (mixed) 516M->424M(1024M), 0.0062324 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 428M->425M(1024M), 0.0017207 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0000357 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0030103 secs]
[GC remark, 0.0010205 secs]
[GC cleanup 445M->403M(1024M), 0.0004671 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000470 secs]
[GC pause (G1 Evacuation Pause) (young) 784M->557M(1024M), 0.0146566 secs]
[GC pause (G1 Evacuation Pause) (mixed) 567M->551M(1024M), 0.0068787 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 555M->551M(1024M), 0.0011959 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0000338 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0036527 secs]
[GC remark, 0.0010939 secs]
[GC cleanup 574M->482M(1024M), 0.0005101 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000974 secs]
[GC pause (G1 Evacuation Pause) (young) 727M->571M(1024M), 0.0119989 secs]
[GC pause (G1 Evacuation Pause) (mixed) 598M->533M(1024M), 0.0076244 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 534M->534M(1024M), 0.0020723 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0000539 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0041576 secs]
[GC remark, 0.0011134 secs]
[GC cleanup 552M->483M(1024M), 0.0005288 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000717 secs]
[GC pause (G1 Evacuation Pause) (young) 766M->603M(1024M), 0.0126820 secs]
[GC pause (G1 Evacuation Pause) (mixed) 629M->573M(1024M), 0.0074521 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 581M->575M(1024M), 0.0018124 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0000476 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0040420 secs]
[GC remark, 0.0011400 secs]
[GC cleanup 596M->513M(1024M), 0.0004641 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0001214 secs]
[GC pause (G1 Evacuation Pause) (young) 757M->620M(1024M), 0.0110161 secs]
[GC pause (G1 Evacuation Pause) (mixed) 647M->574M(1024M), 0.0072116 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 581M->577M(1024M), 0.0024141 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0000238 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0043851 secs]
[GC remark, 0.0012709 secs]
[GC cleanup 600M->532M(1024M), 0.0005828 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000884 secs]
[GC pause (G1 Evacuation Pause) (young) 756M->624M(1024M), 0.0109681 secs]
[GC pause (G1 Evacuation Pause) (mixed) 656M->589M(1024M), 0.0087873 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 590M->590M(1024M), 0.0022765 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0000286 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0044331 secs]
[GC remark, 0.0012342 secs]
[GC cleanup 612M->541M(1024M), 0.0005355 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000769 secs]
执行结束!共生成对象次数:10934

当堆内存变大后明显GC次数变少了
-Xmx4g -Xms4g -XX:+PrintGC -XX:+UseG1GC
[GC pause (G1 Evacuation Pause) (young) 204M->67M(4096M), 0.0126706 secs]
[GC pause (G1 Evacuation Pause) (young) 245M->122M(4096M), 0.0145099 secs]
[GC pause (G1 Evacuation Pause) (young) 300M->180M(4096M), 0.0139503 secs]
[GC pause (G1 Evacuation Pause) (young) 358M->242M(4096M), 0.0139178 secs]
[GC pause (G1 Evacuation Pause) (young) 420M->299M(4096M), 0.0130590 secs]
[GC pause (G1 Evacuation Pause) (young) 477M->369M(4096M), 0.0140252 secs]
[GC pause (G1 Evacuation Pause) (young) 547M->425M(4096M), 0.0124748 secs]
[GC pause (G1 Evacuation Pause) (young) 603M->485M(4096M), 0.0136482 secs]
[GC pause (G1 Evacuation Pause) (young) 663M->549M(4096M), 0.0138015 secs]
[GC pause (G1 Evacuation Pause) (young) 751M->623M(4096M), 0.0163164 secs]
[GC pause (G1 Evacuation Pause) (young) 875M->700M(4096M), 0.0187963 secs]
[GC pause (G1 Evacuation Pause) (young) 986M->793M(4096M), 0.0213809 secs]
[GC pause (G1 Evacuation Pause) (young) 1123M->884M(4096M), 0.0214429 secs]
[GC pause (G1 Evacuation Pause) (young) 1310M->1019M(4096M), 0.0312745 secs]
执行结束!共生成对象次数:13455

2.使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。

sb -u http://localhost:8088/api/hello -n 1000 -c 10

Starting at 2021/5/7 22:08:19
[Press C to stop the test]
998     (RPS: 915.6)
---------------Finished!----------------
Finished at 2021/5/7 22:08:20 (took 00:00:01.3497172)
Status 200:    1000

RPS: 443.1 (requests/second)
Max: 93ms
Min: 0ms
Avg: 1.8ms

  50%   below 1ms
  60%   below 1ms
  70%   below 1ms
  80%   below 1ms
  90%   below 2ms
  95%   below 3ms
  98%   below 6ms
  99%   below 74ms
99.9%   below 93ms

3.（选做） 如果自己本地有可以运行的项目，可以按照 2 的方式进行演练。

直接用HttpServer01、HttpServer02、HttpServer03来测试：
HttpServer01：sb -u  http://localhost:8801 -n 1000 -c 10
Starting at 2021/5/7 20:37:15
[Press C to stop the test]
998     (RPS: 2257.9)
---------------Finished!----------------
Finished at 2021/5/7 20:37:16 (took 00:00:00.6290588)
Status 303:    70
Status 200:    930

RPS: 695.3 (requests/second)
Max: 106ms
Min: 0ms
Avg: 1.3ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 1ms
  90%   below 1ms
  95%   below 2ms
  98%   below 5ms
  99%   below 81ms
99.9%   below 106ms

HttpServer02：sb -u  http://localhost:8802 -n 1000 -c 10
Starting at 2021/5/7 20:38:17
[Press C to stop the test]
995     (RPS: 1990)
---------------Finished!----------------
Finished at 2021/5/7 20:38:18 (took 00:00:00.8135840)
Status 200:    1000

RPS: 609 (requests/second)
Max: 89ms
Min: 0ms
Avg: 1.6ms

  50%   below 1ms
  60%   below 1ms
  70%   below 1ms
  80%   below 1ms
  90%   below 2ms
  95%   below 2ms
  98%   below 8ms
  99%   below 67ms
99.9%   below 89ms

HttpServer03：sb -u  http://localhost:8803 -n 1000 -c 10
Starting at 2021/5/7 20:38:59
[Press C to stop the test]
999     (RPS: 2167)
---------------Finished!----------------
Finished at 2021/5/7 20:39:00 (took 00:00:00.6058422)
Status 303:    102
Status 200:    898

RPS: 699 (requests/second)
Max: 108ms
Min: 0ms
Avg: 1.3ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 1ms
  90%   below 1ms
  95%   below 3ms
  98%   below 5ms
  99%   below 76ms
99.9%   below 108ms

4.（必做） 根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 的总结，提交到 Github。

对于串行和并行GC，平均耗时差不多，但是并行的在最大耗时方面明显优于串行，确实说明CMS的侧重点在于最短回收停顿时间。
G1收集器在平均时间和最大时间上都是最优的，同时也是最复杂的收集器。它取消了分代的划分，用块来存储对象，每次GC时优先回收最有回收价值的块（可被回收对象多的块），
并且采用并行回收，使得回收时间大大缩减。
在其他条件不变的情况下，增大堆内存的大小可以有效减少GC次数。
在没有关闭自适应参数的情况下，在第一次GC的时候就有对象进入老年代了（而不是默认的15次进入老年代），更有效的利用了存储空间。

> Week02 作业题目（周六）：

1.（选做）运行课上的例子，以及 Netty 的例子，分析相关现象。

经过一系列测试后发现，netty在面对多客户端同时请求时的也能正确响应，
而httpserver01、02、03在不同成都上都是返回status303，且随着客户端越多也会相应增长。
sb -u http://localhost:8808/test -n 1000 -c 10

Starting at 2021/5/7 21:36:27
[Press C to stop the test]
998     (RPS: 2661.3)
---------------Finished!----------------
Finished at 2021/5/7 21:36:28 (took 00:00:00.5184934)
Status 200:    1000

RPS: 693 (requests/second)
Max: 83ms
Min: 0ms
Avg: 0.8ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 1ms
  99%   below 61ms
99.9%   below 83ms

2.（必做）写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801 ，代码提交到 Github。

使用HttpClient请求的工具类：
utils.HttpUtil

测试类：
utils.HttpUtilTest

## 操作步骤


### 第二周-周六-作业2

1. 打开 Spring 官网: https://spring.io/
2. 找到 Projects --> Spring Initializr:  https://start.spring.io/
3. 填写项目信息, 生成 maven 项目; 下载并解压。
4. Idea或者Eclipse从已有的Source导入Maven项目。
5. 搜索依赖， 推荐 mvnrepository: https://mvnrepository.com/
6. 搜索 OkHttp 或者 HttpClient，然后在 pom.xml 之中增加对应的依赖。
7. 使用OkHttp
  - 7.1 查找OkHttp官网: https://square.github.io/okhttp/
  - 7.2 参照官方示例编写代码: [OkHttpUtils.java](https://github.com/renfufei/JAVA-000/blob/main/Week_02/homework02/src/main/java/com/renfufei/homework02/OkHttpUtils.java)
8. 使用HttpClient
  - 8.1 查找官网: http://hc.apache.org/
  - 8.2 参照官方示例编写代码: [HttpClientHelper.java](https://github.com/renfufei/JAVA-000/blob/main/Week_02/homework02/src/main/java/com/renfufei/homework02/HttpClientHelper.java)
  - 8.3 执行如果报错, 根据提示，增加 commons-logging 或者其他日志依赖。
9. 执行与测试.
