public class Caldeira {
    private boolean estado;
    private float temperatura;
    private boolean limpa;

    public Caldeira(boolean estado, float temperatura, boolean limpa) {
        this.estado = estado;
        this.temperatura = temperatura;
        this.limpa = limpa;
    }

    public synchronized String ligarCaldeira() {
        System.out.println(Thread.currentThread().getName() + " [ENTROU] ligarCaldeira()");
        System.out.println("Estado atual: " + estado + " | Temperatura: " + temperatura + " | Limpa: " + limpa);
        if (!this.estado) {
            this.estado = true;
            this.limpa = false;
            System.out.println(Thread.currentThread().getName() + " [ALTEROU] estado=true, limpa=false");
            System.out.println("Estado final: " + estado + " | Temperatura: " + temperatura + " | Limpa: " + limpa);
            System.out.println(Thread.currentThread().getName() + " [SAIU] ligarCaldeira()");
            return "Caldeira ligada";
        }
        System.out.println(Thread.currentThread().getName() + " [SAIU] ligarCaldeira()");
        return "Caldeira já está ligada";
    }

    public synchronized String alimentar(int combustivel) {
        System.out.println(Thread.currentThread().getName() + " [ENTROU] alimentar()");
        System.out.println("Estado atual: " + estado + " | Temperatura: " + temperatura + " | Limpa: " + limpa);
        if (this.temperatura + combustivel * 3 <= 1600 && estado) {
            this.temperatura += combustivel * 3;
            System.out.println(Thread.currentThread().getName() + " [ALTEROU] temperatura=" + temperatura);
            System.out.println("Estado final: " + estado + " | Temperatura: " + temperatura + " | Limpa: " + limpa);
            System.out.println(Thread.currentThread().getName() + " [SAIU] alimentar()");
            return "Caldeira combustivel: " + combustivel;
        } else {
            System.out.println(Thread.currentThread().getName() + " [SAIU] alimentar()");
            return "Caldeira está na temperatura máxima ou não está ligada";
        }
    }

    public synchronized String limparCaldeira() {
        System.out.println(Thread.currentThread().getName() + " [ENTROU] limparCaldeira()");
        System.out.println("Estado atual: " + estado + " | Temperatura: " + temperatura + " | Limpa: " + limpa);
        if (this.estado) {
            System.out.println(Thread.currentThread().getName() + " [SAIU] limparCaldeira()");
            return "Caldeira ainda ligada, não pode ser limpa";
        } else {
            this.limpa = true;
            System.out.println(Thread.currentThread().getName() + " [ALTEROU] limpa=true");
            System.out.println("Estado final: " + estado + " | Temperatura: " + temperatura + " | Limpa: " + limpa);
            System.out.println(Thread.currentThread().getName() + " [SAIU] limparCaldeira()");
            return "Caldeira limpa";
        }
    }

    public synchronized String desligarCaldeira() {
        System.out.println(Thread.currentThread().getName() + " [ENTROU] desligarCaldeira()");
        System.out.println("Estado atual: " + estado + " | Temperatura: " + temperatura + " | Limpa: " + limpa);
        if (this.estado) {
            this.estado = false;
            System.out.println(Thread.currentThread().getName() + " [ALTEROU] estado=false");
            System.out.println("Estado final: " + estado + " | Temperatura: " + temperatura + " | Limpa: " + limpa);
            System.out.println(Thread.currentThread().getName() + " [SAIU] desligarCaldeira()");
            return "Caldeira desligada";
        } else {
            System.out.println(Thread.currentThread().getName() + " [SAIU] desligarCaldeira()");
            return "Caldeira ainda desligada";
        }
    }
}
