package ru.vershinin.lesson8.example;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * Message
 *
 * @author Вершинин Пётр
 */
public class Message {
    private int id;
    private double id1;
    private float id2;
    private String data;
    private ArrayList<Integer> list;
    private float[] fArray;
    private MyEnum enumEl;

    public Message(int id, double id1, float id2, String data)
    {
        this.id = id;
        this.id1 = id1;
        this.id2 = id2;
        this.data = data;
        list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        fArray=new float[]{1.25f,2.036f,4.14f};
        enumEl = MyEnum.EnumElement2;
    }



    public byte[] Serialize() throws Exception
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        Class cls =  this.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(Field field : fields)
        {
            Class<?> type = field.getType();
            field.setAccessible(true);
            if(type==ArrayList.class)
            {
                ParameterizedType genType = (ParameterizedType)field.getGenericType();
                Class<?> argType = (Class<?>)genType.getActualTypeArguments()[0];
                if(argType==Integer.class)
                {
                    ArrayList<Integer> list =(ArrayList<Integer>) field.get(this);
                    dos.writeInt(list.size());
                    for(int i : list)
                    {
                        dos.writeInt(i);
                    }
                }
                System.out.println("After "+type+": "+dos.size());
            }
            if(type.isArray())
            {
                if(type.getComponentType()==float.class)
                {
                    float[] fArray = (float[])field.get(this);
                    dos.writeInt(fArray.length);
                    for(int i =0; i<fArray.length; i++)
                    {
                        dos.writeFloat(fArray[i]);
                    }
                }
                System.out.println("After "+type+": "+dos.size());
            }

            if(type.isEnum())
            {
                if(type==MyEnum.class)
                {
                    MyEnum myEnum = (MyEnum)field.get(this);
                    dos.writeInt(myEnum.getId());
                    System.out.println("After "+type+": "+dos.size());
                }
            }

            if(type==float.class)
            {
                dos.writeFloat((Float)field.get(this));
                System.out.println("After "+type+": "+dos.size());
            }

            if(type==int.class)
            {
                dos.writeInt((Integer)field.get(this));
                System.out.println("After "+type+": "+dos.size());
            }

            if(type==String.class)
            {
                dos.writeUTF((String)field.get(this));
                System.out.println("After " + type + ": " + dos.size());
            }

            if(type==double.class)
            {
                dos.writeDouble((Double)field.get(this));
                System.out.println("After "+type+": "+dos.size());
            }
        }
        dos.close();
        System.out.println("**********************************************");
        return baos.toByteArray();
    }

    public void Message(byte[] data) throws Exception
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bais);

        Class cls =  this.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(Field field : fields)
        {
            Class<?> type = field.getType();
            field.setAccessible(true);
            if(type==ArrayList.class)
            {
                ParameterizedType genType = (ParameterizedType)field.getGenericType();
                Class<?> argType = (Class<?>)genType.getActualTypeArguments()[0];
                int size = dis.readInt();
                if(argType==Integer.class)
                {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    for(int i =0; i<size; i++)
                    {
                        list.add(dis.readInt());
                    }
                    field.set(this,list);
                }

            }
            if(type.isArray())
            {
                if(type.getComponentType()==float.class)
                {
                    int length = dis.readInt();
                    float[] fArray = new float[length];
                    for(int i =0; i<fArray.length; i++)
                    {
                        fArray[i]=dis.readFloat();
                    }
                    field.set(this,fArray);
                }
            }

            if(type.isEnum())
            {
                if(type==MyEnum.class)
                {
                    MyEnum myEnum = MyEnum.values()[dis.readInt()];
                    field.set(this,myEnum);
                }
            }

            if(type==float.class)
            {
                field.set(this, dis.readFloat());
            }

            if(type==int.class)
            {
                field.set(this, dis.readInt());
            }

            if(type==String.class)
            {
                field.set(this, dis.readUTF());
            }

            if(type==double.class)
            {
                field.set(this,dis.readDouble());
            }
        }
        dis.close();
    }

    public void Display() throws Exception
    {
        Class cls =  this.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(Field field : fields)
        {
            Class<?> type = field.getType();
            field.setAccessible(true);
            System.out.println("Name: "+field.getName()+"  Type: "+type+"  Value: "+field.get(this));
            if(type== ArrayList.class)
            {
                ParameterizedType genType = (ParameterizedType)field.getGenericType();
                Class<?> argType = (Class<?>)genType.getActualTypeArguments()[0];
                if(argType==Integer.class)
                {
                    ArrayList<Integer> list =(ArrayList<Integer>) field.get(this);
                    for(int i : list)
                    {
                        System.out.println(i);
                    }
                }
            }
            if(type.isArray())
            {
                if(type.getComponentType()==float.class)
                {
                    float[] fArray = (float[])field.get(this);
                    for(int i =0; i<fArray.length; i++)
                    {
                        System.out.println(fArray[i]);
                    }
                }
            }

            if(type.isEnum())
            {
                if(type==MyEnum.class)
                {
                    MyEnum myEnum = (MyEnum)field.get(this);
                    System.out.println(myEnum);
                }
            }
        }
    }
}
