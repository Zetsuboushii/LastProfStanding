# Hofmann - probability of dying

- $ t $: time [mins] passed since starting game
- $ P(t) $: Probability, that Hofmann disintegrates in this step

$$
P(t) = \frac{1}{\sigma} \cdot \varphi \left(\frac{t - \mu}{\sigma}\right)
= \frac{1}{\sigma\sqrt{\pi}} \cdot e^{-\left(\frac{t-\mu}{\sigma}\right)^2} = \frac{1}{\sigma\sqrt{\pi}} \cdot e^{\left(
-\frac{1}{\sigma^2} \cdot \left(t - \mu\right)^2\right)}
$$

And therefore:

$$
\frac{\mathrm{d}P}{\mathrm{d}t} = -2 \cdot \frac{t - \mu}{\sigma^3 \sqrt{\pi}} \cdot e^{-\left(\frac{t -
\mu}{\sigma}\right)^2}
$$
