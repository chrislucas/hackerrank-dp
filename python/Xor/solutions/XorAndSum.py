'''
https://www.hackerrank.com/challenges/xor-and-sum/problem
'''


def toDec(str_bin):
    a, e = 0, 0
    for i in range(len(str_bin) - 1, -1, -1):
        a += (1 if str_bin[i] == '1' else 0) << e
        e += 1
    return a


def modular_sum(a, b, m):
    return ((a % m) + (b % m)) % m


def naive_test(a, b):
    acc = 0
    for i in range(0, 3141600):
        x = a ^ (b << i)
        acc = modular_sum(acc, x, 1000000007)
    return acc


def solver():
    bin_a = input()
    bin_b = input()
    a, b = toDec(bin_a), toDec(bin_b)
    return naive_test(a, b)


print(solver())

if __name__ == '__main__':
    pass
