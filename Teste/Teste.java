package Teste;
import java.util.*;

public class Teste {

	public static void main(String[] args) {
		Scanner teclado=new Scanner(System.in);
		System.out.println("Digite o tamanho da matriz a ser escalonada! (linhas x colunas)");
		int linhas=teclado.nextInt();
		int colunas=teclado.nextInt();
		System.out.println("Digite sua matriz!");
		double[][] matriz=new double[linhas][colunas];
		for (int i=0;i<linhas;i++) {
			for (int j=0;j<colunas;j++) {
				matriz[i][j]=teclado.nextDouble();
			}
		}


		int posicaoLinha=0;
		int posicaoColuna=0;
		double aux=0;
		for (int i=0;i<linhas;i++) {
			boolean checagem=false;
			if (matriz[posicaoLinha][posicaoColuna]==0) {//ter certeza que não começa com 0
				checagem=true;
				for (int m=posicaoLinha;m<linhas&&checagem;m++) {
					if (matriz[m][posicaoColuna]!=0) {
						checagem=false;
						Double[] auxiliar = new Double[colunas];
						for (int j=0;j<colunas;j++) {
							auxiliar[j]=matriz[m][j];
						}
						for (int k=0;k<colunas;k++ ) {
							matriz[m][k]=matriz[posicaoLinha][k];
						}
						for (int l=0;l<colunas;l++) {
							matriz[posicaoLinha][l]=auxiliar[l];
						}
					}
				}
			}
			if (matriz[posicaoLinha][posicaoColuna]!=1&&!checagem) { //transformar em 1
				double transformacao=matriz[posicaoLinha][posicaoColuna];
				for (int j=0;j<colunas;j++) {
					matriz[posicaoLinha][j]=matriz[posicaoLinha][j]/transformacao;
				}
			}
			posicaoLinha++;
			for (int l=posicaoLinha;l<=linhas-posicaoLinha&&!checagem;l++) {
				if (matriz[l-1][posicaoColuna]==0) {
					checagem=true;
					for (int m=l;m<linhas&&checagem;m++) {
						if (matriz[m][posicaoColuna]!=0) {
							checagem=false;
							Double[] auxiliar = new Double[colunas];
							for (int j=0;j<colunas;j++) {
								auxiliar[j]=matriz[l-1][j];
							}
							for (int k=0;k<colunas;k++ ) {
								matriz[l-1][k]=matriz[m][k];
							}
							for (int p=0;p<colunas;p++) {
								matriz[m][p]=auxiliar[p];
							}
						}
					}
				}
			}
			for (int l=posicaoLinha;l<linhas&&!checagem;l++) {
				if (matriz[l][posicaoColuna]!=0) {
					aux=matriz[l][posicaoColuna]/matriz[posicaoLinha-1][posicaoColuna];
					for (int j=posicaoColuna;j<colunas;j++) {
						matriz[l][j]=(matriz[l][j]) - (matriz[posicaoLinha-1][j]*aux);
					}
				}
			}posicaoColuna++;
		}
		posicaoLinha=0;
		posicaoColuna=0;
		int auxiliar=0;
		for (int i=1;i<linhas;i++) {
			posicaoColuna=i-1;
			auxiliar=0;
			for (int j=0;j<colunas;j++) {
				posicaoColuna++;
				if (posicaoLinha+auxiliar+1<linhas&&posicaoColuna+auxiliar<colunas&&matriz[posicaoLinha][posicaoColuna]!=0&&matriz[posicaoLinha+auxiliar+1][posicaoColuna]!=0) {
					aux=matriz[posicaoLinha][posicaoColuna]/matriz[posicaoLinha+auxiliar+1][posicaoColuna];
					for (int k=posicaoColuna;k<colunas;k++) {
						matriz[posicaoLinha][k]=(matriz[posicaoLinha][k]) - (matriz[posicaoLinha+auxiliar+1][k]*aux);
					}
				}auxiliar++;


			}posicaoLinha++;
		}

		for (int i=0;i<linhas;i++) {
			for (int j=0;j<colunas;j++) {
				if (matriz[i][j] == 0) {
					matriz[i][j] = 0;
				}
				System.out.printf(" %.1f  ",matriz[i][j]);
			}
			System.out.println("");
		}System.out.println("");
		System.out.println("A matriz escalonada é: ");
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				if (matriz[i][j] == 0) {
					matriz[i][j] = 0;
				}
				if (j == 0) {
					System.out.printf("%.1f ", matriz[i][j]);
				} else {
					System.out.printf(" %.1f  ", matriz[i][j]);
				}
			}
			System.out.println("");
		}
		System.out.println("");
		for (int i = 0; i < linhas; i++) {
			;
			if (matriz[i][matriz[i].length - 1] < 0) {
				System.out.printf("O valor da corrente I%d  é %.1f A. Ela está na direção contrária ao esperado.\n", (i + 1), matriz[i][matriz[i].length - 1]);
			}
			else {
				System.out.printf("O valor da corrente I%d  é %.1f A.\n", (i + 1), matriz[i][matriz[i].length - 1]);
			}
		}
	}

}
