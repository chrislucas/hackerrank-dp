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


def run():
    bin_a = input()
    bin_b = input()
    max_length = max(len(bin_a), len(bin_b))
    reverse_a = [0] * max_length
    for i in range(0, len(bin_a)):
        reverse_a[i] = bin_a[len(bin_a)-1-i]
    reverse_b = [0] * max_length
    for i in range(0, len(bin_b)):
        reverse_b[i] = bin_b[len(bin_b)-1-i]

    return reverse_a, reverse_b

def solver():
    reverse_a, reverse_b = run()
    limit = 314519
    mod = 1000000007
    count_bits_b = 0
    power = 1
    acc = 0
    i = 0
    while i < max_length:
        count_bits_b += int(reverse_b[i])
        m = count_bits_b if reverse_a[i] == '0' else limit - count_bits_b + 1
        acc = (acc + power * m) % mod
        power = (power << 1) % mod
        i += 1

    while i <= limit:
        acc = (acc + power * count_bits_b) % mod
        power = (power << 1) % mod
        i += 1

    for i in range(0, max_length):
        acc = (acc + power * count_bits_b) % mod
        power = (power << 1) % mod
        count_bits_b -= int(reverse_b[i])
        i += 1

    return acc


def solver_2():
    reverse_a, reverse_b = run()
    return


print(solver())

if __name__ == '__main__':
    pass
