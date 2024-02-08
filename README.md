## A Java GUI demo to play and test strategies for [100 prisoner problems](https://en.wikipedia.org/wiki/100_prisoners_problem).

Applied double buffering to eliminate the flicker effect and utilized recursion to simulate repeated Bernoulli trials.

To start the game, simply run GameGo.java, you will get into this interface

<img src="figs/iShot_2024-02-08_11.12.39.png" style="zoom:50%;" />

Click `Free Play`, you can play as one of the prisoners and test your strategy.

This video elaborates on the problem and introduces the best strategy for this problem:

https://www.youtube.com/watch?v=iSNsgj1OCLA&t=50s

Click `1 prisoner trial` and the first cell to get the result of the first prisoner following the best strategy.

Click `know their fate` and the first cell to test the best strategy with all 100 prisoners and get their outcome.

Click `Best Idea` and the first cell to test the best strategy for 300 rounds to test the probability

<img src="figs/result.png" style="zoom:67%;" />

## 百囚犯问题Java GUI
具体问题的解释可参考 https://www.bilibili.com/video/BV17W4y1S7WR?vd_source=b1e7b556c3c545be2e8271ca1e9063b1
运行GameGo.java，单击`Free Play`及第一格测试自己的策略，单击`Best Idea`和第一格，重复300次最优策略，可查最优策略的成功概率
