package problems;

public class CoinChange {

    /**
     * Q = q-esima moeda do conjunto que estou usando para
     * ver se chego na solucao
     * */
    public static int topDown(int [] coins, int target, int Q) {
        /**
         * Se o valor que queremos atingir for zero entao temos
         * uma solucao, conjutno vazio
         * */
        if(target == 0)
            return 1;
        /**
         * se o valor que queremos atingir for menor que 0 nao ha solucao
         * */
        else if(target < 0)
            return 0;
        /**
         * Se ja tentamos usar todas as moedas do conjunto
         * */
        else if(Q < 1 && target > 0)
            return 0;
        /**
         * int target: valor que se quer alcancar
         * caso 1) target - coins[Q-1] usando a Q-esima moeda o valor de targert eh diminuido
         *
         * caso 2) Q--, nao usei a q-esima moeada, passemos para a proxima
         *
         */
        int useCoin = topDown(coins, target - coins[Q-1], Q);
        int notUseCoin = topDown(coins, target, Q-1);
        return useCoin + notUseCoin;
    }

    /**
     * So para ver se entendi a pegada
     * */
    public static int anotherTopDownApproach(int [] coins, int target, int Q) {
        /**
         * Se o valor que queremos atingir for zero entao temos
         * uma solucao, conjutno vazio
         * */
        if(target == 0)
            return 1;
        /**
         * se o valor que queremos atingir for menor que 0 nao ha solucao
         * */
        else if(target < 0)
            return 0;
        /**
         * usamos todas as moedas e nao c valor objetivo ainda eh maior que 0 ?
         * entao nao a solucao
         * */
        else if(Q == coins.length && target > 0)
            return 0;

        int s = anotherTopDownApproach(coins, target - coins[Q], Q);
        int n = anotherTopDownApproach(coins, target, Q+1);
        return s + n;
    }

    /**
     *
     * int Q, quantidade de moedas no conjunto
     *
     * loop [from i=1 to target]
     *      constroi a solucao de baixo para cima ate atingir o valor target
     *      loop from j=0 to Q
     *      testa se a j-esima moeda pode ser adicionada a solucao
     *
     * Exemplo target = 3 coins = {1,2,3}]
     * quando i == 3  e j == 0 vamos testar se  coins[j] pode entrar na solucao para i == 3
     * quando i == 3  e j == 1 vamos testar se  coins[j] pode entrar na solucao para i == 3
     * quando i == 3  e j == 2 vamos testar se  coins[j] pode entrar na solucao para i == 3
     * no final vemos que a table[i][j] tera valores 1,2,3 respectivamente porque para i == 3
     * adimitimos que podemos ter uma solucao so com a moeda de valor 1, com as moedas 1 e 2
     * e com a moeda de valor 3. Assim se quisessemos para por ai, para target == 3 e coins ={1,2,3}
     * a solucao eh table[target][Q] que tem o valor 3
     *
     * a table no indice (i,j) guarda a solucao para o problema quando o target == i e tentamos
     * adicionar a j-esima moeda no conjutno
     * */
    public static int bottomUp(int [] coins, int target, int Q) {
        /**
         *
         * Usando memoization. Criamos uma tabela com o numero de linhas
         * igual ao valor que queremos atingir
         *
         * teremos solucoes de 0 <= i <= target onde i sao os valores entre 0
         * e valor que se quer atingir (abordagem bottomUp) e 0 <= j < Q
         * onde j e a n-esima moeda do conjunto passado como argumento dessa funcao
         *
         * */
        int [][] table = new int[target+1][Q];
        /**
         * Quando o valor objetivo for igual a 0 so existe uma solucao, conjunto vazio
         * */
        for (int i = 0; i <Q ; i++)
            table[0][i] = 1;
        /**
         * O loop externo permite construir a solucao partindo do valor
         * 1 ate o valor que queremos atingir (bottomUp)
         * */
        for (int i = 1; i <target+1 ; i++) {
            /**
             * Esse loop interno percorre a quantidade de moedas que temos no conjunto
             * */
            for (int j = 0; j<Q; j++) {
                /**
                 * se i - coin[j] < 0, a j-esima moeda nao faz parte da solucao do problema
                 * do contrario usamos o valor da (i-coins[j])-esima solucao para compor a solucao
                 * atual
                 * */
                int ithSolution = i-coins[j];
                int a = ithSolution  < 0 ? 0 : table[ithSolution][j] ;
                /**
                 * Se j > 0 pegamos o valor
                 * */
                int b =  j > 0 ? table[i][j-1] : 0;
                /**
                 * Quantas solucoes temos quando o nosso valor objeto for i
                 * e
                 * 1) tentamos incluir a j-esima moeda a solucao
                 * 2) quando j > 0
                 *      se a j-esima faz parte da solucao
                 *
                 *
                 * */
                table[i][j] = a + b;
            }
        }
        return table[target][Q-1];
    }

    public static int bottomUpReduceSpace(int [] coins, int target, int Q) {
        int table [] = new int[target+1];
        table[0] = 1;
        for (int i = 0; i <Q ; i++) {
            for (int j = coins[i]; j<=target ; j++) {
                int ithSolution = j - coins[i];
                table[j] += table[ithSolution];
            }
        }
        return table[target];
    }

    public static void testTopDownApproach() {
        int [] set = {1,2,3};
        int k = 4;
        System.out.println(topDown(set, k, set.length));
        set = new int [] {2, 3, 5, 6};
        k = 10;
        System.out.println(anotherTopDownApproach(set, k, 0));
    }


    public static void testBottomupApproach() {
        int [] set = {1,2,3};
        int k = 4;
        System.out.println(bottomUp(set, k, set.length));
        set = new int [] {3,2,1};
        k = 4;
        System.out.println(bottomUpReduceSpace(set, k, set.length));

        set = new int [] {1,2,3};
        k = 6;
        System.out.println(bottomUp(set, k, set.length));
        set = new int [] {3,2,1};
        k = 6;
        System.out.println(bottomUpReduceSpace(set, k, set.length));

        set = new int [] {2,3,5,6};
        k = 10;
        System.out.println(bottomUpReduceSpace(set, k, set.length));
    }

    public static void main(String[] args) {
        testBottomupApproach();
    }
}
