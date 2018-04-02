'''
https://www.hackerrank.com/challenges/xor-and-sum/problem
https://en.wikipedia.org/wiki/Binary_number#Binary_counting
'''


def to_dec(str_bin):
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
    a, b = to_dec(bin_a), to_dec(bin_b)
    return naive_test(a, b)


_LIMIT_SUM = 314159
_M = 1000000007


def solver2():
    bin_a = input()
    bin_b = input()
    _sz_a = len(bin_a)
    _sz_b = len(bin_b)
    buffer_binary_a = [0] * _sz_a
    acc_buffer_binary_b = [0] * _sz_b
    acc_buffer_inv_binary_b = [0] * _sz_b
    acc = 0
    power_of_2 = 1
    for x in range(_sz_a - 1, -1, -1):
        buffer_binary_a[_sz_a - 1 - x] = 1 if bin_a[x] == '1' else 0

    for x in range(_sz_b - 1, -1, -1):
        idx = _sz_b - 1 - x
        acc_buffer_binary_b[idx] = 1 if bin_b[x] == '1' else 0
        if x < _sz_b - 1:
            acc_buffer_binary_b[idx] += acc_buffer_binary_b[idx - 1]

    for x in range(0, _sz_b):
        acc_buffer_inv_binary_b[x] = 1 if bin_b[x] == '1' else 0
        if x > 0:
            acc_buffer_inv_binary_b[x] += acc_buffer_inv_binary_b[x - 1]

    for x in range(0, _LIMIT_SUM):
        xth_bit_a = buffer_binary_a[x] if x < _sz_a else 0
        xth_bit_b = acc_buffer_binary_b[x] if x < _sz_b else acc_buffer_binary_b[_sz_b - 1]
        if xth_bit_a == 1:
            acc += (((_LIMIT_SUM+1-xth_bit_b)%_M)*(power_of_2%_M))%_M
        else:
            acc += ((xth_bit_b%_M)*(_M%power_of_2))%_M
        power_of_2 = (power_of_2%_M)*(2%_M)%_M

    for x in range(0, _sz_b):
        acc += (power_of_2*acc_buffer_inv_binary_b[_sz_b-1-x])%_M
        acc %= _M
        power_of_2 = (power_of_2%_M)*(2%_M)%_M

    return acc


solver2()

if __name__ == '__main__':
    pass
