import math
holder = int(input("How many primes do you want generated?"))
runner = 1
counter = 1;

def isPrime(number):
    for x in range(2, int(math.sqrt(number)+1)): #The square root of the number
        # print("Test:",x)                       #is the last check to see
        if (number % x) == 0:                    #if the number has any factors
            #print("False")
            return False
    # print("True")
    return True

while counter <= holder:
    if(isPrime(runner)):
        print(runner)
        counter += 1
    runner += 1
    # print("Runner:",runner)


holder = input("Press Enter to exit")
## The input holds the program in place
