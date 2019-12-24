import math
math_operators_precedence = \
    {"^": 4,
     "*": 3,
     "/": 3,
     "+": 2,
     "-": 2,
     "(": 0,
     ")": 0}

def main():

    print("Please enter mathematical expression. For exit type 'exit'")
    while True:
        user_input = input()
        if user_input == "exit":
            print("Bye.")
            break
        else:
            exprassion = prepare_exprassion(user_input)
            result = calculate_exprassion(exprassion)
            print(user_input + " = " + str(result))


def prepare_exprassion(string):
    operationList = []
    exprassionList = []
    string = string[::-1]
    string = [str(x) for x in string]
    while string:
        element = string.pop()
        if is_digit(element):
            number = element
            while True:
                
                if len(string) == 0:
                    break
                
                element = string.pop()
                if is_math_operator(element) or element == "(" or element == ")":
                    break
                
                number += element

            exprassionList.append(number)

        if is_math_operator(element):           
            if len(operationList) > 0:
                while math_operators_precedence[operationList[-1]] >= math_operators_precedence[element] and element != "^":
                    exprassionList.append(operationList.pop())
                    if len(operationList) == 0:
                        break                   
            operationList.append(element)

        if element == "(":
            operationList.append(element)

        if element == ")":
            while(operationList[-1] != "("):               
                exprassionList.append(operationList.pop())
            operationList.pop() # removes opening bracket

    while(operationList):    
        exprassionList.append(operationList.pop())
        
    return exprassionList


def calculate_exprassion(exprassion):
    calculationList = []
    
    for element in exprassion:
        if is_math_operator(element):
            operand_1 = calculationList.pop()
            operand_2 = calculationList.pop()
            if element == "^":
                result = math.pow(float(operand_2), float(operand_1))
            else:
                result = eval(str(operand_2) + element + str(operand_1))
            calculationList.append(result)
        else:
            calculationList.append(element)
            
    return calculationList.pop()

def is_digit(value):
    return value in ("0","1","2","3","4","5","6","7","8","9")

def is_math_operator(value):
    return value in ("+", "-", "*", "/","^")


main()