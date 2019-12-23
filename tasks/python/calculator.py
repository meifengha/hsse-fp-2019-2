def readOperator(expr: str):
  isNumber = expr[0].isdigit()
  i = 0
  if (isNumber):
    point = False
    for c in expr:
      if (c.isdigit()):
        i += 1
      elif (c == '.'):
        if point:
          raise 2
        i += 1
        point = True
      else:
        break
  else:
    if (expr[0] not in ['+', '-', '*', '/', '(', ')']):
      raise 2;
    i = 1
    
  return (expr[:i], expr[i:])

def isFloat(num: str):
  try:
    float(num)
    return True
  except ValueError:
    return False
    
sanitize = lambda expr: "".join(expr.split()).replace(',', '.')
  
def infixToPostfix(expr: str):
  expr = sanitize(expr)
  high = ['*', '/']
  low = ['+', '-']

  stack = []
  opStack = []
  while (len(expr)):
    op, expr = readOperator(expr)
    
    if (op.isdigit() or isFloat(op)):
      stack += [op]
    elif (op == '('):
      opStack += [op]
    elif (op == ')'):
      while (1):
        if (opStack[-1] == '('):
          opStack = opStack[:-1]
          break
        else:
          stack, opStack = stack + [opStack[-1]], opStack[:-1]
    else:
      while (1):
        if ((not len(opStack)) or opStack[-1] == '('):
          opStack += [op]
          break
        else:
          if (opStack[-1] in low) and (op in high):
            opStack += [op]
            break
            
          if ((opStack[-1] in high) and (op in high)) or ((opStack[-1] in low) and (op in low)):
            stack, opStack = stack + [opStack[-1]], opStack[:-1] + [op]
            break
            
          if (opStack[-1] in high) and (op in low):
            stack, opStack = stack + [opStack[-1]], opStack[:-1]
          else:
            break
          
  stack += opStack[::-1]
  return stack
  
def calculate(expr: str):
  expr = sanitize(expr)
  ops = ['+', '-', '*', '/']
  postfix = infixToPostfix(expr)
  stack = []
  for op in postfix:
    if (op in ops):
      rhs, lhs, stack = stack[-2], stack[-1], stack[:-2]
      stack += [eval('%s %s %s' % (rhs, op, lhs))]
    else:
      stack += [op]
      
  return stack[0]
 
def outputExpression(expr: str):
  print("Expression: \"%s\"" % expr)
  print("Result is: %s" % calculate(expr))
  print("Postfix notation is: %s" % ' '.join(infixToPostfix(expr)))

def main():
  outputExpression('(1.1 + 5 * 2) * 10 + 5')
  print('\n')
  outputExpression('12852,643 / 37 * 0,32 + 31 / 6 + 52') # Thank you, @Polykek2K!
  

if __name__ == "__main__":
  main()