package net.weg.topcar;

public class Main {
//    static Scanner sc = new Scanner(System.in);
//    static Cliente clienteLogado;
//
//    private static void cadastroUsuarios() {
//        Cliente novoCliente = new net.weg.topcar.model.usuarios.Cliente("Winter", "108.414.879-01", "Chettus", 18);
//        novoCliente.adicionarUsuario();
//        novoCliente = new Vendedor("Vendedor", "111.111.111-11", "vendedor", 37, 30000);
//        novoCliente.adicionarUsuario();
//        novoCliente = new Gerente("Gerente", "222.222.222-22", "gerente", 24, 50000);
//        novoCliente.adicionarUsuario();
//    }
//
//    public static void main(String[] args) {
//        cadastroUsuarios();
//        int escolha;
//        System.out.println("Bem-vindo ao sistema!");
//        do {
//            System.out.println("""
//
//                    1 - Cadastrar-se;
//                    2 - Logar;
//                    3 - Ver automóveis;
//                    4 - Ver automóvel específico;
//                    0 - Sair.
//                    """);
//            System.out.print("> ");
//            escolha = sc.nextInt();
//
//            switch (escolha) {
//                case 0 -> System.out.println("Obrigado por usar o nosso sistema!");
//
//                case 1 -> cadastrarUsuario();
//                case 2 -> login();
//                case 3 -> verAutomoveis();
//                case 4 -> verAutomovelEspecifico();
//                default -> System.out.println("Opção inválida!");
//            }
//        } while (escolha != 0);
//        System.exit(0);
//    }
//
//    public static void cadastrarUsuario() {
//        String nome, cpf, senha;
//        int idade;
//        do {
//            System.out.print("Nome: ");
//            nome = sc.nextLine();
//        } while (nome.equals(""));
//
//        do {
//            System.out.print("CPF: ");
//            cpf = sc.nextLine();
//
//            if (Cliente.procurarUsuario(cpf) != null) {
//                System.out.println("Esse CPF já possui uma conta!");
//                cpf = "";
//            }
//        } while (cpf.equals(""));
//
//        do {
//            System.out.print("Senha: ");
//            senha = sc.nextLine();
//        } while (senha.equals(""));
//
//        do {
//            System.out.print("Idade: ");
//            idade = Integer.parseInt(sc.nextLine());
//        } while (idade == 0);
//
//        Cliente novoCliente = new net.weg.topcar.model.usuarios.Cliente(nome, cpf, senha, idade);
//        novoCliente.adicionarUsuario();
//        System.out.println("Usuário cadastrado!");
//    }
//
//    public static void verAutomoveis() {
//        System.out.println("Nossos automóveis:");
//        ArrayList<Automovel> automoveisComprar = new ArrayList<Automovel>();
//
//        for (Automovel automovel : Automovel.getListaAutomoveis()) {
//            if (!automovel.isComprado()) {
//                automoveisComprar.add(automovel);
//            }
//        }
//        System.out.println(automoveisComprar);
//    }
//
//    public static void verAutomovelEspecifico() {
//        System.out.print("Código do automóvel: ");
//        String codigo = sc.nextLine();
//
//        System.out.println(Automovel.procurarAutomovel(codigo));
//    }
//
//    public static void verMeusAutomoveis() {
//        System.out.println("Seus automóveis:");
//        System.out.println(clienteLogado.getListaAutomoveis());
//    }
//
//    public static void login() {
//        System.out.print("CPF: ");
//        String cpf = sc.next();
//
//        System.out.print("Senha: ");
//        String senha = sc.next();
//        try {
//            clienteLogado = Cliente.login(cpf, senha);
//            menuPrincipal();
//        } catch (ObjetoNaoEncontradoException exception){
//            System.out.println(exception.getMessage());
//            System.out.println("""
//                    Deseja realizar o cadastro?
//                    1 - Sim;
//                    Outro - Não.
//                    """);
//            int escolha = sc.nextInt();
//            if (escolha == 1) {
//                cadastrarUsuario();
//            }
//        } catch (SenhaIncorretaException exception){
//            System.out.println(exception.getMessage());
//        }
//    }
//
//    public static void venderAutomovel() {
//        Cliente clienteComprando;
//        Automovel automovelComprando;
//
//        do {
//
//            System.out.print("CPF do usuário: ");
//            String cpf = sc.nextLine();
//
//            clienteComprando = Cliente.procurarUsuario(cpf);
//
//            if (clienteComprando == null) {
//                System.out.println("Usuário não encontrado!");
//                continue;
//            } else if (clienteComprando.equals(clienteLogado)) {
//                System.out.println("Você não pode vender para si mesmo!");
//                continue;
//            }
//            break;
//        } while (true);
//
//        do {
//
//            System.out.print("Código do carro: ");
//            String codigo = sc.nextLine();
//
//            automovelComprando = Automovel.procurarAutomovel(codigo);
//
//            if (automovelComprando == null) {
//                System.out.println("Automóvel não encontrado!");
//                continue;
//            } else if (automovelComprando.isComprado()) {
//                System.out.println("Esse automóvel já foi comprado!");
//                continue;
//            }
//            break;
//        } while (true);
//
//        if (clienteLogado instanceof Funcionario funcionario) {
//            funcionario.venderAutomovel(automovelComprando, clienteComprando);
//            System.out.println("Compra realizada!");
//        } else {
//            System.out.println("Você não possui as permissões necessárias!");
//        }
//    }
//
//    public static void procurarUsuario() {
//        System.out.print("CPF do usuário: ");
//        String cpf = sc.nextLine();
//
//        System.out.println(Cliente.procurarUsuario(cpf));
//    }
//
//    public static void verPagamento() {
//        if (clienteLogado instanceof Funcionario funcionario) {
//            System.out.println("Seu pagamento:");
//            System.out.println(funcionario.verPagamento());
//        }
//    }
//
//    public static void registrarAutomovel() {
//        String codigo, modelo, marca, tipoCombustivel, placa, cor, estado;
//        int ano, quilometragem;
//        double preco;
//
//        Automovel automovelTeste;
//        Automovel automovelCadastrado;
//
//        System.out.println("A informação 'placa' é opcional!");
//
//        do {
//            System.out.print("Código do automóvel: ");
//            codigo = sc.nextLine();
//
//            automovelTeste = Automovel.procurarAutomovel(codigo);
//
//            if (automovelTeste != null) {
//                System.out.println("Automóvel já cadastrado!");
//            } else if (!codigo.equals("")) {
//                break;
//            }
//
//        } while (true);
//
//        do {
//            System.out.print("Modelo: ");
//            modelo = sc.nextLine();
//        } while (modelo.equals(""));
//
//        do {
//            System.out.print("Marca: ");
//            marca = sc.nextLine();
//        } while (marca.equals(""));
//
//        do {
//            System.out.print("Tipo de combustível: ");
//            tipoCombustivel = sc.nextLine();
//        } while (tipoCombustivel.equals(""));
//
//        System.out.print("Placa: ");
//        placa = sc.nextLine();
//
//        do {
//            System.out.print("Cor: ");
//            cor = sc.nextLine();
//        } while (cor.equals(""));
//
//        do {
//            System.out.print("Estado ('novo' ou 'seminovo'): ");
//            estado = sc.nextLine();
//        } while (!estado.equals("novo") && !estado.equals("seminovo"));
//
//        do {
//            System.out.print("Ano: ");
//            ano = Integer.parseInt(sc.nextLine());
//        } while (ano == 0);
//
//        System.out.print("Quilometragem: ");
//        quilometragem = Integer.parseInt(sc.nextLine());
//
//        do {
//            System.out.print("Preço: ");
//            preco = Double.parseDouble(sc.nextLine());
//        } while (preco == 0);
//
//        int escolha;
//
//        do {
//            System.out.println("""
//                    1 - Carro;
//                    2 - Moto;
//                    3 - Quadriciclo.
//                    """);
//
//            System.out.print("> ");
//            escolha = Integer.parseInt(sc.nextLine());
//        } while (escolha < 1 || escolha > 3);
//
//        if (clienteLogado instanceof Gerente gerente) {
//            switch (escolha) {
//                case 1 -> {
//                    String marcha, tipoCarroceria;
//
//                    do {
//                        System.out.print("Marcha: ");
//                        marcha = sc.nextLine();
//                    } while (marcha.equals(""));
//
//                    do {
//                        System.out.print("Tipo de carroceria: ");
//                        tipoCarroceria = sc.nextLine();
//                    } while (tipoCarroceria.equals(""));
//
//                    automovelCadastrado = new Carro(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, estado, marcha, tipoCarroceria);
//                    gerente.registrarAutomovel(automovelCadastrado);
//                }
//                case 2 -> {
//                    String partida;
//                    int cilindradas;
//
//                    do {
//                        System.out.print("Partida: ");
//                        partida = sc.nextLine();
//                    } while (partida.equals(""));
//
//                    do {
//                        System.out.print("Cilindradas: ");
//                        cilindradas = Integer.parseInt(sc.nextLine());
//                    } while (cilindradas == 0);
//
//                    automovelCadastrado = new Moto(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, estado, partida, cilindradas);
//                    gerente.registrarAutomovel(automovelCadastrado);
//                }
//                case 3 -> {
//                    automovelCadastrado = new Quadriciclo(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, estado);
//                    gerente.registrarAutomovel(automovelCadastrado);
//                }
//            }
//            System.out.println("Automóvel registrado!");
//        } else {
//            System.out.println("Você não possui as permissões necessárias!");
//        }
//    }
//
//    public static void removerAutomovel() {
//        System.out.print("Código do automóvel: ");
//        String codigo = sc.nextLine();
//        Automovel automovelRemover = Automovel.procurarAutomovel(codigo);
//
//        if (automovelRemover == null) {
//            System.out.println("Automóvel não encontrado!");
//        } else if (automovelRemover.isComprado()) {
//            System.out.println("O carro já foi comprado! Impossível realizar a remoção!");
//        } else {
//            if (clienteLogado instanceof Gerente gerente) {
//                gerente.removerAutomovel(automovelRemover);
//                System.out.println("Automóvel removido!");
//            } else {
//                System.out.println("Você não possui as permissões necessárias!");
//            }
//        }
//    }
//
//    public static void editarAutomovel() {
//        System.out.println("Código do automóvel que deseja editar: ");
//        String codigo = sc.nextLine();
//
//        Automovel automovel = Automovel.procurarAutomovel(codigo);
//
//        if (automovel == null) {
//            System.out.println("Automóvel não encontrado!");
//        } else if (automovel.isComprado()) {
//            System.out.println("O automóvel já foi comprado! Impossível realizar a edição!");
//        } else {
//            String modelo, marca, tipoCombustivel, placa, cor, estado;
//            int ano, quilometragem;
//
//            System.out.println("Caso não deseje mudar um valor, apenas passe sem digitar nada (em números, digite 0)!");
//
//            System.out.print("Modelo: ");
//            modelo = sc.nextLine();
//
//            if (modelo.equals("")) {
//                modelo = automovel.getModelo();
//            }
//
//            System.out.print("Marca: ");
//            marca = sc.nextLine();
//
//            if (marca.equals("")) {
//                marca = automovel.getMarca();
//            }
//
//            System.out.print("Tipo de combustível: ");
//            tipoCombustivel = sc.nextLine();
//
//            if (tipoCombustivel.equals("")) {
//                tipoCombustivel = automovel.getTipoCombustivel();
//            }
//
//            System.out.print("Placa (digite '0' para deixar a placa vazia): ");
//            placa = sc.nextLine();
//
//            if (placa.equals("")) {
//                placa = automovel.getPlaca();
//            } else if (placa.equals("0")) {
//                placa = "";
//            }
//
//            System.out.print("Cor: ");
//            cor = sc.nextLine();
//
//            if (cor.equals("")) {
//                cor = automovel.getCor();
//            }
//
//            do {
//                System.out.print("Estado ('novo' ou 'seminovo'): ");
//                estado = sc.nextLine();
//            } while (!estado.equals("novo") && !estado.equals("seminovo"));
//
//            System.out.print("Ano: ");
//            ano = Integer.parseInt(sc.nextLine());
//
//            if (ano == 0) {
//                ano = automovel.getAno();
//            }
//
//            System.out.print("Quilometragem: ");
//            quilometragem = Integer.parseInt(sc.nextLine());
//
//            if (quilometragem == 0) {
//                quilometragem = automovel.getQuilometragem();
//            }
//
//            if (clienteLogado instanceof Gerente gerente) {
//                Automovel novoAutomovel;
//                if (automovel instanceof Carro carro) {
//                    String marcha, tipoCarroceria;
//
//                    System.out.print("Marcha: ");
//                    marcha = sc.nextLine();
//
//                    if (marcha.equals("")) {
//                        marcha = carro.getMarcha();
//                    }
//
//                    System.out.print("Tipo da carroceria: ");
//                    tipoCarroceria = sc.nextLine();
//
//                    if (tipoCarroceria.equals("")) {
//                        tipoCarroceria = carro.getTipoCarroceria();
//                    }
//                    novoAutomovel = new Carro(automovel.getCODIGO(), modelo, ano, marca, tipoCombustivel, automovel.getPreco(), quilometragem, placa, cor, estado, marcha, tipoCarroceria);
//                    gerente.editarAutomovel(automovel, novoAutomovel);
//                } else if (automovel instanceof Moto moto) {
//                    String partida;
//                    int cilindradas;
//
//                    System.out.print("Partida: ");
//                    partida = sc.nextLine();
//
//                    if (partida.equals("")) {
//                        partida = moto.getPartida();
//                    }
//
//                    System.out.print("Cilindradas: ");
//                    cilindradas = Integer.parseInt(sc.nextLine());
//
//                    if (cilindradas == 0) {
//                        cilindradas = moto.getCilindradas();
//                    }
//
//                    novoAutomovel = new Moto(automovel.getCODIGO(), modelo, ano, marca, tipoCombustivel, automovel.getPreco(), quilometragem, placa, cor, estado, partida, cilindradas);
//                    gerente.editarAutomovel(automovel, novoAutomovel);
//                } else if (automovel instanceof Quadriciclo) {
//                    novoAutomovel = new Quadriciclo(automovel.getCODIGO(), modelo, ano, marca, tipoCombustivel, automovel.getPreco(), quilometragem, placa, cor, estado);
//                    gerente.editarAutomovel(automovel, novoAutomovel);
//                }
//                System.out.println("Automóvel editado!");
//            } else {
//                System.out.println("Você não possui as permissões necessárias!");
//            }
//        }
//
//    }
//
//    public static void editarPreco() {
//        System.out.println("Código do automóvel que deseja editar: ");
//        String codigo = sc.nextLine();
//
//        Automovel automovel = Automovel.procurarAutomovel(codigo);
//
//        if (automovel == null) {
//            System.out.println("Automóvel não encontrado!");
//        } else if (automovel.isComprado()) {
//            System.out.println("Automóvel já está comprado! impossível realizar a edição!");
//        } else {
//            double preco;
//
//            do {
//                System.out.println("Novo preço: ");
//                preco = Double.parseDouble(sc.nextLine());
//            } while (preco == 0);
//
//            if (clienteLogado instanceof Gerente gerente) {
//                gerente.editarPreco(automovel, preco);
//                System.out.println("Preço editado!");
//            } else {
//                System.out.println("Você não possui as permissões necessárias!");
//            }
//        }
//    }
//
//    public static void registrarUsuario() {
//        String nome, cpf, senha;
//        int idade;
//
//        do {
//            System.out.print("Nome: ");
//            nome = sc.nextLine();
//        } while (nome.equals(""));
//
//        do {
//            System.out.print("CPF: ");
//            cpf = sc.nextLine();
//
//            if (Cliente.procurarUsuario(cpf) != null) {
//                System.out.println("Esse CPF já possui uma conta!");
//                cpf = "";
//            }
//        } while (cpf.equals(""));
//
//        do {
//            System.out.print("Senha: ");
//            senha = sc.nextLine();
//        } while (senha.equals(""));
//
//        do {
//            System.out.print("Idade: ");
//            idade = Integer.parseInt(sc.nextLine());
//        } while (idade == 0);
//
//        int escolha;
//
//        do {
//            System.out.println("""
//                    1 - Cliente;
//                    2 - Vendedor.
//                    """);
//
//            System.out.print("> ");
//            escolha = Integer.parseInt(sc.nextLine());
//        } while (escolha < 1 || escolha > 2);
//
//        if (clienteLogado instanceof Gerente gerente) {
//
//            if (escolha == 2) {
//                Double salario;
//                do {
//                    System.out.print("Salário: ");
//                    salario = Double.parseDouble(sc.nextLine());
//                } while (salario == null);
//
//                gerente.registrarUsuario(new Vendedor(nome, cpf, senha, idade, salario));
//            } else {
//                gerente.registrarUsuario(new net.weg.topcar.model.usuarios.Cliente(nome, cpf, senha, idade));
//            }
//            System.out.println("Usuário adicionado!");
//        } else {
//            System.out.println("Você não possui as permissões necessárias!");
//        }
//
//
//    }
//
//    public static String solicitaCPF() {
//        System.out.print("CPF do usuário: ");
//        return sc.nextLine();
//    }
//
//    public static void editarUsuario() {
//        String nome, senha;
//
//
//        System.out.println("CPF do usuário que deseja editar: ");
//        String cpf = sc.nextLine();
//
//        Cliente cliente = Cliente.procurarUsuario(cpf);
//
//        if (cliente == null) {
//            System.out.println("Usuário não encontrado!");
//        } else if (cliente instanceof Gerente) {
//            System.out.println("O usuário pesquisado é um gerente! Impossível fazer a remoção!");
//        } else {
//            System.out.println("Caso não deseje mudar um valor, apenas passe sem digitar nada!");
//
//            System.out.print("Nome: ");
//            nome = sc.nextLine();
//
//            if (nome == "") {
//                nome = cliente.getNome();
//            }
//
//            System.out.print("Senha: ");
//            senha = sc.nextLine();
//
//            if (senha == "") {
//                senha = cliente.getSenha();
//            }
//
//            if (clienteLogado instanceof Gerente gerente) {
//                Cliente novoCliente;
//                if (cliente instanceof Vendedor vendedor) {
//                    Double salario;
//
//                    System.out.print("Salário: ");
//                    salario = Double.parseDouble(sc.nextLine());
//
//                    if (salario == null) {
//                        salario = vendedor.getSalario();
//                    }
//
//                    novoCliente = new Vendedor(nome, cliente.getCpf(), senha, cliente.getIdade(), salario);
//                    gerente.editarUsuario(novoCliente);
//                } else {
//                    novoCliente = new net.weg.topcar.model.usuarios.Cliente(nome, cliente.getCpf(), senha, cliente.getIdade());
//                    gerente.editarUsuario(novoCliente);
//                }
//            }
//        }
//    }
//
//    public static void verVendedores() {
//        if (clienteLogado instanceof Gerente gerente) {
//            System.out.println(gerente.verVendedores());
//        }
//    }
//
//    public static void verClientes() {
//        if (clienteLogado instanceof Gerente gerente) {
//            System.out.println(gerente.verClientes());
//        }
//    }
//
//    public static void verPagamentoVendedores() {
//        if (clienteLogado instanceof Gerente gerente) {
//            System.out.println(gerente.verPagamentoVendedores());
//        }
//    }
//
//    public static void verPagamentoVendedor() {
//        System.out.print("CPF do vendedor: ");
//        String cpf = sc.nextLine();
//
//        Cliente cliente = Cliente.procurarUsuario(cpf);
//        if (cliente == null) {
//            System.out.println("Usuário não encontrado!");
//        } else if (!(cliente instanceof Vendedor vendedor)) {
//            System.out.println("O usuário encontrado não é um vendedor!");
//        } else {
//            if (clienteLogado instanceof Gerente gerente) {
//                System.out.println(gerente.verPagamentoVendedor(vendedor));
//            } else {
//                System.out.println("Você não possui as permissões necessárias!");
//            }
//        }
//    }
//
//    public static void menuPrincipal() {
//        do {
//            System.out.print(clienteLogado.menu());
//            System.out.print("0 - Deslogar." +
//                    "\n\n> ");
//            int escolha = sc.nextInt();
//
//            switch (escolha) {
//                case 0 -> clienteLogado = null;
//                case 1 -> verAutomoveis();
//                case 2 -> verAutomovelEspecifico();
//                case 3 -> verMeusAutomoveis();
//            }
//            if (clienteLogado instanceof Funcionario funcionario) {
//                switch (escolha) {
//                    case 4 -> venderAutomovel();
//                    case 5 -> procurarUsuario();
//                    case 6 -> verPagamento();
//                }
//                if (clienteLogado instanceof Gerente gerente) {
//                    switch (escolha) {
//                        case 7 -> registrarAutomovel();
//                        case 8 -> removerAutomovel();
//                        case 9 -> editarAutomovel();
//                        case 10 -> editarPreco();
//                        case 11 -> registrarUsuario();
//                        case 12 -> {
//                            String cpf = solicitaCPF();
//                            System.out.println(
//                                    gerente.removerUsuario(cpf));
//                        }
//                        case 13 -> editarUsuario();
//                        case 14 -> verVendedores();
//                        case 15 -> verClientes();
//                        case 16 -> verPagamentoVendedores();
//                        case 17 -> verPagamentoVendedor();
//                    }
//                }
//            }
//        } while (clienteLogado != null);
//    }
}
