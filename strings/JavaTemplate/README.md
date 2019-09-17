# Desafio 1: Strings

##Resolução

      O algoritmo de formatação abaixo realiza a formatação do texto recebido de acordo
      com o limite de caracteres estipulado e se deve ou não justificar o texto.
     
      O texto recebido é valiado em relação ao seu tamanho, caso seja nulo ou tenha um
      tamanho = 0 é lançada uma exception do tipo: {@link IllegalArgumentException}
     
      Em seguida é realizado um split do texto: {@code String[] lines = text.split("\n");}
      com o intuito de separar o texto recebido em linhas, caso o texto tenha mais de uma linha.
      O próximo passo é iterar sobre cada linha obtida no passo anterior e então realizar o split
      do texto pelo caractere de espaço " " afim de separar todas as palavras do texto em um array:
      {@code String[] words = lines[l].split(SPACE_CONSTANT);}, uma vez tendo um array com todas
      palavras o próximo passo do algoritimo é iterar sobre esse array e adicionar em uma
      {@link LinkedList}, que irá representar uma nova linha formatada cada palavra encontrada mais
      uma representação de espaço controlando se o tamanho atual da linha é menor ou igual o limite
      parametrizado ({@code  if (actualLen + words[i].length() <= limit)}).
      Sempre que o tamanho atual da linha chegar ao limite um nova linha ({@link LinkedList}) é instanciado.
     
      O próximo passo é verificar se o texto deverá ou não ser justificado. Se sim, utilizamos uma implementação
      baseada no Algoritmo de Markov. Armazenamos em um {@link ArrayList} as posições de referência de cada " "
      (espaço) da linha e também calculamos a quantidade de espaços restantes para que o texto atinja o limite estabelecido:
      ({@code int availableSpaces = limit - lineLen;}). Em seguida baseado nas posições do ArrayList de posições
      vazias selecionamos de forma aleatória o index da LinkedList e preenchemos com espaços, até que o valor da variável
      {@code availableSpaces} seja igual a 0. Com isso teremos o texto justificado a direita e a esquerda
     
      O último passo é retornar a String final com o texto formatado. Para isso iteramos sobre o ArrayList que
      contém os LinkedList que representam cada linha e por fim iteramos cada uma das LinkedList e armazenamos
      seu texto e um StringBuilder.

## Build

`maven clean package`


## Execução com Configuração DEFAULT
`java -jar StringFormatter-1.0-SNAPSHOT.jar`

## Execução Parametrizada
`arg1 - texto a ser formatado`

`arg2 - limite de caracteres por linha`

`arg3 - justificacao`

`java -jar StringFormatter-1.0-SNAPSHOT.jar [arg1] [arg2] [arg3]`

Exemplo:
`java -jar StringFormatter-1.0-SNAPSHOT.jar "texto grande que precisa ser formato respeitando o limite de caracteres por linha" 20 true`