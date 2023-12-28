<h1 align="left">Seguros</h1>

###

<p align="left">Olá meu nome é Alcides, e aqui estarei explicando um pouco mais sobre o desenvolvimento da aplicação.</p>

###

<h2 align="left">Configuração</h2>

###

<p align="left">Realizar o clone do repositório:<br>git clone https://github.com/albraz/challenge.git<br><br>Com o IntelliJ aberto acessar: <br>Settings -> Gradle -> Gradle JVM<br><br>Ajustar para versão 17<br><br>Executador o comando gradle clean build<br><br>Com o Docker instalado e configurado, executar o comando docker compose up. Caso não tenha o docker instalado é possível executar a aplicação normalmente através do comando Shift+F10<br><br>Importar para o Insomnia ou Postman a collection Seguros.postman_collection.json presente na estrutura do projeto</p>

###

<h2 align="left">Detalhes</h2>

###

<p align="left">Foi utilizada a arquitetura hexagonal para criar a estrutura do projeto.<br><br>Podemos observar que o mesmo é dividido em três partes sendo aplicação, camada de entrada, domain com as regras de negócio, e infraestrutura responsável pela persistência dos dados.<br><br>Devido a simplicidade da aplicação evitei a utilização de design-patterns o que poderia aumentar a complexidade do código, porém disponibilizei um Builder como exemplo.<br><br>Para calcular o preço tarifado utilizei um simples ENUM com um método chamado getFee para realizar o cálculo, algumas vantagens e desvantagens sobre o método escolhido.<br><br>- O ideal seria que o valor da taxa fosse persistido em um banco de dados.<br>- A desvantagem de utilizar um Enum é que a taxa não pode ser alterada em tempo de execução e não é possível adicionar novos tipos de produtos sem alterar o código-fonte, aumentado o acoplamento.<br><br>- A vantagem é que o Enum é uma classe final, portanto não pode ser extendida, e é thread-safe.<br>- Outra vantagem é chamar o método getFee() diretamente no Enum, sem a necessidade de instanciar um objeto.</p>

###

<p align="left">Java (JDK 17)<br>Gradle 8.2.1<br>Docker 24.0.7<br>Git<br>H2 DB</p>
###
<h2 align="left">Stack</h2>
###

<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" alt="java logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/gradle/gradle-plain.svg" height="40" alt="gradle logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" height="40" alt="docker logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/ubuntu/ubuntu-plain.svg" height="40" alt="ubuntu logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/linux/linux-original.svg" height="40" alt="linux logo"  />
</div>

###