import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.StringReader;


public class LGUI extends JFrame {
    JTextArea t1;
    static JTextArea t2;
    JButton b1;
    JButton b2;
    JButton b3;
    JPanel jp1;
    BufferedReader input = null;
    Analysis analysis;

    public LGUI() {
        JFrame jf = new JFrame();
        jf.setTitle("词法编译器");
        Container c = jf.getContentPane();
        t1 = new JTextArea(10, 30);
        t2 = new JTextArea(10, 30);
        JScrollPane js1=new JScrollPane(t1);
        JScrollPane js2=new JScrollPane(t2);
        js1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        js2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        t1.setLineWrap(true);
        t1.setSize(400, 300);
        t2.setSize(400, 300);
        b1 = new JButton("退出");
        b2 = new JButton("开始");
        b3=new JButton("清除");
        b2.addActionListener(this::actionPerformed);
        b1.addActionListener(this::actionPerformed);
        b3.addActionListener(this::actionPerformed);
        jp1 = new JPanel();
        jp1.add(b1);
        jp1.add(b3);
        jp1.add(b2);
        c.add(js1, BorderLayout.WEST);
        c.add(js2, BorderLayout.EAST);
        c.add(jp1, BorderLayout.SOUTH);
        jf.setSize(800, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setLocation(200, 200);
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == b1) {
            System.exit(0);
        }
        if(obj==b3){ //清除
            t1.setText("");
            t2.setText("");
        }
        if (obj == b2) { //分析过程在analysis
            analysis=new Analysis();
            char a[];
            try {
                input = new BufferedReader(new StringReader(t1.getText()));
                String line;
                while((line=input.readLine())!=null){
                    line.replace("\\s+", ""); //多个空格合并一个
                    a = line.toCharArray(); //一行变为一个数组
                    for (char c : a
                    ) {
                        analysis.list1.offer(c);//将元素插入到队列
                    }
                    analysis.ananlyze();
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }
}
