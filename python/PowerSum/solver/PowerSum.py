'''
https://www.hackerrank.com/challenges/the-power-sum/problem
'''

'''
De quantas formas e possivel escrever um numero M
na forma de soma de potencias N
'''


def top_down(base, exp, acc=1):
    X = acc ** exp
    if X > base:
        return 0
    elif X == base:
        return 1
    u = top_down(base, exp, acc + 1)
    v = top_down(base - X, exp, acc + 1)
    return u + v


def another_top_down(base, exp, acc=1):
    x = base - (acc ** exp)
    if x < 0:  # se  acc ^ exp > que a base x < 0 portanto acc ^ exp nao faz parte da solucao
        return 0
    elif x == 0:  # acc ^ x == base entao base - (acc ^ x) == 0 logo acc ^ x faz parte da solucao
        return 1
    u = another_top_down(base, exp, acc + 1)  # sem adicionar acc ^ exp a solucao
    v = another_top_down(x, exp, acc + 1)  # adicionando acc ^ exp a solucao
    return u + v


def bottom_up_approach(base, exp):
    from math import sqrt
    acc = 1
    table = [0] * (base + 1)
    for i in range(2, int(sqrt(base) + 1)):
        x = acc ** i
        acc += 1

    return table[base - 1]


'''
E se eu quisesse saber de quantas formas e possivel escrever
um numero como potencias de 1 a P
'''


def run():
    n = int(input())
    p = int(input())
    print(another_top_down(n, p))


def test():
    print(top_down(10, 2), another_top_down(10, 2))
    print(top_down(4, 2), another_top_down(4, 2))
    print(top_down(4, 1), another_top_down(4, 1))
    print(top_down(10, 1), another_top_down(10, 1))
    print(top_down(100, 3), another_top_down(100, 3))
    print(top_down(100, 2), another_top_down(100, 2))


test()

if __name__ == '__main__':
    pass
