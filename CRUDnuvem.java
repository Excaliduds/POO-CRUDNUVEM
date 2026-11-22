package produtos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CRUDnuvem {

	 public static void main(String[] args) {

	        // Conexão Railway

		 String url = "jdbc:mysql://ballast.proxy.rlwy.net:40298/railway";

		 String usuariobanco = "root";

		 String bancosenha = "lgBVxWXXwfOoyxJsvpQAhYkACjUfFrQD";
		 
	
		 Scanner sc = new Scanner(System.in);
		
		 	// Minha identificação nome e matrícula
	        System.out.println("Nome: Eduardo Pinheiro da Luz | RA: 0025085");

	        try {

	            Connection conexao = DriverManager.getConnection(
	                url, usuariobanco, bancosenha
	            );

	            int opcao = -1;

	            while (opcao != 0) {

	                System.out.println("\nSelecione uma das seguintes Opções:\n"
	                +"1 - Inserir | 2 - Listar | 3 - Atualizar | 4 - Deletar | 0 - Sair\n");

	                opcao = sc.nextInt();
	                sc.nextLine();

	                switch (opcao) {

	                // Case para Inserir um produto
	                case 1: 

	                    System.out.print("\nCódigo do produto: ");
	                    int codigo = sc.nextInt();
	                    sc.nextLine();

	                    System.out.print("Nome do produto: ");
	                    String nome = sc.nextLine();

	                    System.out.print("Preço do produto: ");
	                    float preco = sc.nextFloat();

	                    String sql = "INSERT INTO Produtos (CODIGO, NOME, PRECO) VALUES (?, ?, ?)";

	                    PreparedStatement stmt = conexao.prepareStatement(sql);

	                    stmt.setInt(1, codigo);
	                    stmt.setString(2, nome);
	                    stmt.setFloat(3, preco);

	                    stmt.executeUpdate();
	                    stmt.close();

	                    System.out.println("Produto cadastrado com sucesso!");

	                    break;

	                    // Case para listar os produtos
	                    case 2:

	                        System.out.println("\nDigite o código do produto:");
	                        int codigo2 = sc.nextInt();

	                        String sql2 = "SELECT * FROM Produtos WHERE CODIGO = ?";

	                        PreparedStatement stmt2 = conexao.prepareStatement(sql2);

	                        stmt2.setInt(1, codigo2);

	                        ResultSet rs = stmt2.executeQuery();

	                        while (rs.next()) {

	                            System.out.println("Código: " + rs.getInt("CODIGO"));
	                            System.out.println("Nome produto: " + rs.getString("NOME"));
	                            System.out.println("Preço: " + rs.getFloat("PRECO"));

	                        }

	                        rs.close();
	                        stmt2.close();

	                        break;

	                      // Case para atualizar os dados de um produto existente    
	                    case 3: 

	                        System.out.println("Digite o código do produto para atualizar:");
	                        int codPesquisa = sc.nextInt();

	                        System.out.println("Digite o novo valor do produto:");
	                        float novoPreco = sc.nextFloat();

	                        String sqlUp = "UPDATE Produtos SET PRECO = ? WHERE CODIGO = ?";

	                        PreparedStatement stmUp = conexao.prepareStatement(sqlUp);

	                        stmUp.setFloat(1, novoPreco);
	                        stmUp.setInt(2, codPesquisa);

	                        int linhaAfetada = stmUp.executeUpdate();

	                        if (linhaAfetada > 0) {

	                            System.out.println("Preço atualizado com sucesso!!!");

	                        } else {

	                            System.out.println("Error: Produto não localizado!!!");

	                        }

	                        stmUp.close();

	                        break;
	                        
	                      //Case para deletar um item   
	                    case 4:

	                        System.out.println("Digite o código do produto a ser deletar:");
	                        int codDeleta = sc.nextInt();

	                        String sqlDelete = "DELETE FROM Produtos WHERE CODIGO = ?";

	                        PreparedStatement stmDelete = conexao.prepareStatement(sqlDelete);

	                        stmDelete.setInt(1, codDeleta);

	                        int linhasDeletadas = stmDelete.executeUpdate();

	                        if (linhasDeletadas > 0) {

	                            System.out.println("Produto deletado com sucesso!!!");

	                        } else {

	                            System.out.println("Error: Produto não encontrado!!!");

	                        }

	                        stmDelete.close();

	                        break;

	                    // Finalizar as Opcões e encerrar o programa    
	                    case 0:

	                        System.out.println("Programa finalizado com sucesso!!!");

	                        break;

	                    default:

	                        System.out.println("Opção inválida!!!");

	                }

	            }

	            conexao.close();

	        } catch (Exception e) {

	            System.out.println("Erro de cadastro: " + e.getMessage());
	            
	        }

	        sc.close();

	    }

	}
