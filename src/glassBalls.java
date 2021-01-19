public class glassBalls {

    /*
    f(n,k,s) = (n/k) + k -1

    when n==number of floors
    k == number of division
    s == number of floors per division

    n = s*k so s=n/k
    and number of maximum checks (of what floor the ball will broke if we throw it from that floor)
    is k checks from the beginning of each division, the ball will break at the last division, so we will check all s
    floors at this division and the ball will break only in the n floor(last)
    so we will be making s+k-1 checks ! thats bring us to =  (n/k) +k -1.

    seeking for the optimal k (optimal division of the building)

    a^2+b^2 >= 2ab
    (sqrt(a))^2 + ((sqrt(b)^2) >= 2(sqrt(ab))

    place a=n/k
    and b=k
    and we will get infimum of 2*sqrt(n), so we will choose k=sqrt(n) to get to this infimum !!

    improvment is to divert n like this:
    n = 1+2+3+4+...+k = ((k+1)k) / 2
    thats gives us
    k ~~ sqrt(2n)
    thats improvment from 2*sqrt(n)


     */
}
