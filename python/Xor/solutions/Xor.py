from math import log10, floor


def isPowerOf2(n):
    return (n & (n - 1)) == 0


print([(isPowerOf2(i) and i > 1, i) for i in range(0, 100)])


def sum_n_xor(n):
    acc = 1
    for x in range(2, n + 1):
        acc = acc ^ x
    return acc


def sum_n_xor2(n):
    o = n & 3
    if o == 0:
        return n
    elif o == 1:
        return 1
    elif o == 2:
        return n + 1
    else:
        return 0


def count_bits_base2(n):
    return floor(log10(n) / log10(2)) + 1


print(count_bits_base2(10))
print(count_bits_base2(1 << 50))
print(count_bits_base2(15))
'''
print([(x, sum_n_xor(x)) for x in range(4, 25)])
print([(x, sum_n_xor2(x)) for x in range(4, 25)])
'''

if __name__ == '__main__':
    pass
