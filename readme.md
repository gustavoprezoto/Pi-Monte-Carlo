# Cálculo de Pi por Método Monte Carlo


## Integrantes

- FELIPE YUJI SHIRAE, R.A.:1997408
- GABRIEL HENRIQUE DE ALMEIDA SOUZA, R.A.:2504162
- GUSTAVO PREZOTO BOCA, R.A.: 2250373
- JOÃO PEDRO SANTOS DE ARAÚJO, R.A.: 2525852
- VICTOR MATHEUS TAVARES RAFAEL, R.A.: 2525917

## Descrição do problema
Este projeto implementa o cálculo de 𝜋 utilizando o método de Monte Carlo, uma técnica probabilística baseada na 
geração de pontos aleatórios. O método calcula 𝜋 ao estimar a razão entre os pontos que caem dentro de um círculo e o 
total de pontos gerados dentro de um quadrado que circunscreve o círculo. São apresentadas três soluções: sequencial, 
paralela e distribuída, cada uma explorando diferentes abordagens para otimizar o cálculo e escalar o processamento
conforme o ambiente disponível.

## Como executar os algoritmos

### Sequencial e paralelo

É necessário apenas executar o método main de cada classe respectiva (Sequencial e paralelo).

### Distribuido

É necessário executar uma instância do método main da classe MonteCarloDistribuido.MonteCarloMaster e 
a partir disso executar 4 instâncias de MonteCarloDistribuido.MonteCarloSlave