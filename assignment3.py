def compute(n):
    if n < 10:
        out = n ** 2
        #negative numbers also tested
    elif n < 20:
        out = 1
        for i in range(1, n-9):
            #range changed from 1 to n-10
            out *= i
    else:
        lim = n - 20
        out = lim * (lim+1)
        #sum of first n numbers formula implimented
        out = out / 2 
    print(out)


n = int(input("Enter an integer: "))
compute(n)

