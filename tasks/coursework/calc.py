arg1 = int(input("First argument: "))
arg2 = int(input("Second argument: "))

select = input("Select an operation +, -, *, /: ")

ops = {'+': (lambda x, y: (x + y)),
       '-': (lambda x, y: (x - y)),
       '*': (lambda x, y: (x * y)),
       '/': (lambda x, y: (x / y))}

if select in ops:
    print('Result: ', ops[select](arg1, arg2))
else:
    print('Invalid operation')

