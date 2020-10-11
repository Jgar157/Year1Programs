"""
Determines the factors of an inputted number.
__author__: Jairo Garciga
"""

import math

num = int(input("What number would you like to find the factors for?"))
prime_num_check = num
current_num_found = 0
counter = 1
prime_list = []
list_of_numbers = [num]
if 1 not in list_of_numbers:
    list_of_numbers.append(1)
for x in range(2, math.ceil(math.sqrt(num)+1)):

    if num % x == 0:
        list_of_numbers.append(x)

        while prime_num_check % x == 0:
            prime_list.append(x)
            prime_num_check //= x

        current_num_found += 1
        if x not in list_of_numbers:
            list_of_numbers.append(num // x)
            current_num_found += 1

    if x % 2500 == 0:
        print(str(x) + " Total Passes---Factors Found:", current_num_found)


prime_list.append(prime_num_check)
prime_list.sort()
list_of_numbers.sort()
print(prime_list)

# Printing the the factors in rows of 10
print("\nThese are the factors of:", num)
count = 0
for x in list_of_numbers:
    count += 1
    print(str(x), end=' ')
    if count % 10 == 0:
        print()

stop = input("Press enter to exit")
