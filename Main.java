import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        Caldeira caldeira = new Caldeira(false, 0, true);
        Thread ligar_caldeira = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread ligar_caldeira iniciada");
                String resultado = caldeira.ligarCaldeira();
                System.out.println("Resultado: " + resultado);
                System.out.println("Thread ligar_caldeira finalizada");
            }
        });

        Thread alimentar_caldeira = new Thread(new Runnable() {
            int combustivelAleatorio = 50 + (int)(Math.random() * 251);
            @Override
            public void run() {
                System.out.println("Thread alimentar_caldeira iniciada - combustível: " + combustivelAleatorio);
                String resultado = caldeira.alimentar(combustivelAleatorio);
                System.out.println("Resultado: " + resultado);
                System.out.println("Thread alimentar_caldeira finalizada");
            }
        });

        Thread limpar_caldeira = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread limpar_caldeira iniciada");
                String resultado = caldeira.limparCaldeira();
                System.out.println("Resultado: " + resultado);
                System.out.println("Thread limpar_caldeira finalizada");
            }
        });

        Thread desligar_caldeira = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread desligar_caldeira iniciada");
                String resultado = caldeira.desligarCaldeira();
                System.out.println("Resultado: " + resultado);
                System.out.println("Thread desligar_caldeira finalizada");
            }
        });

        ligar_caldeira.setPriority(Thread.MIN_PRIORITY);
        alimentar_caldeira.setPriority(Thread.MIN_PRIORITY);
        limpar_caldeira.setPriority(Thread.MIN_PRIORITY);
        desligar_caldeira.setPriority(Thread.MAX_PRIORITY);

        System.out.println("Iniciando threads da caldeira...");
        ligar_caldeira.start();
        alimentar_caldeira.start();
        limpar_caldeira.start();
        desligar_caldeira.start();

        try {
            ligar_caldeira.join();
            alimentar_caldeira.join();
            limpar_caldeira.join();
            desligar_caldeira.join();
        } catch (InterruptedException e) {
            System.out.println("Thread foi interrompida: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Todas as operações da caldeira foram concluídas.");
    }

}
