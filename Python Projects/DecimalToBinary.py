T = True
target = int(input("Please input the number you want to convert binary."))
binary = ""
exponentoftwo = 2

while exponentoftwo > 1:
    while exponentoftwo <= target:
        exponentoftwo *= 2
    exponentoftwo = exponentoftwo//2
    while exponentoftwo > 1:
        if target >= exponentoftwo:
            binary += "1"
            target -= exponentoftwo
        else:
            binary += "0"
        exponentoftwo = exponentoftwo//2
binary += str(target)
print(binary)
input("Thank you for using me :)")
