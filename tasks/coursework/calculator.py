operations = \
    {
        "/": lambda x, y: x / y,
        "*": lambda x, y: x * y,
        "+": lambda x, y: x + y,
        "-": lambda x, y: x - y,
        "%": lambda x, y: x % y,
        "//": lambda x, y: x // y,
        "^": lambda x, y: x ** y
    }

numb1 = float(input())
arithAct = input("+, -, *, /, %, //, ^ ->  ")
numb2 = float(input())

if arithAct in operations:
    print(operations[arithAct](numb1, numb2))
