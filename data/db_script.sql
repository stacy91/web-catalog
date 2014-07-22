USE [master]
GO
/****** Object:  Database [myOnlineShopDB]    Script Date: 22.07.2014 12:52:38 ******/
CREATE DATABASE [myOnlineShopDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'myOnlineShopDB', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\myOnlineShopDB.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'myOnlineShopDB_log', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\myOnlineShopDB_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [myOnlineShopDB] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [myOnlineShopDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [myOnlineShopDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [myOnlineShopDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [myOnlineShopDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [myOnlineShopDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [myOnlineShopDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [myOnlineShopDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [myOnlineShopDB] SET  MULTI_USER 
GO
ALTER DATABASE [myOnlineShopDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [myOnlineShopDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [myOnlineShopDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [myOnlineShopDB] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [myOnlineShopDB]
GO
/****** Object:  Table [dbo].[Arrival]    Script Date: 22.07.2014 12:52:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Arrival](
	[GoodsId] [int] NOT NULL,
	[UserId] [int] NOT NULL,
	[Amount] [int] NOT NULL,
	[Time] [datetime] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Brands]    Script Date: 22.07.2014 12:52:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Brands](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[BrandName] [nvarchar](80) NOT NULL,
 CONSTRAINT [PK_Brands] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Goods]    Script Date: 22.07.2014 12:52:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Goods](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[ModelId] [int] NOT NULL,
	[ScreenSizeId] [int] NOT NULL,
	[MemoryId] [int] NOT NULL,
	[ColorId] [int] NOT NULL,
	[ImageURL] [nvarchar](max) NULL,
	[Price] [decimal](5, 2) NOT NULL,
	[AmmountInStock] [int] NOT NULL,
 CONSTRAINT [PK_Goods] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Goods_Color]    Script Date: 22.07.2014 12:52:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Goods_Color](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Color] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Goods_Color] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Goods_Memory]    Script Date: 22.07.2014 12:52:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Goods_Memory](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Memory] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Goods_Memory] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Goods_Model]    Script Date: 22.07.2014 12:52:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Goods_Model](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[BrandId] [int] NOT NULL,
	[ModelName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Goods_Model] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Goods_ScreenSize]    Script Date: 22.07.2014 12:52:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Goods_ScreenSize](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[ScreenSize] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Goods_ScreenSize] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Orders_Sales]    Script Date: 22.07.2014 12:52:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders_Sales](
	[GoodsId] [int] NOT NULL,
	[UserId] [int] NOT NULL,
	[IsSold] [bit] NOT NULL,
	[Amount] [int] NOT NULL,
	[Time] [datetime] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Users]    Script Date: 22.07.2014 12:52:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Role] [nvarchar](50) NOT NULL,
	[Login] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[Arrival]  WITH CHECK ADD  CONSTRAINT [FK_Income_Goods1] FOREIGN KEY([GoodsId])
REFERENCES [dbo].[Goods] ([Id])
GO
ALTER TABLE [dbo].[Arrival] CHECK CONSTRAINT [FK_Income_Goods1]
GO
ALTER TABLE [dbo].[Arrival]  WITH CHECK ADD  CONSTRAINT [FK_Income_Users1] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Arrival] CHECK CONSTRAINT [FK_Income_Users1]
GO
ALTER TABLE [dbo].[Goods]  WITH CHECK ADD  CONSTRAINT [FK_Goods_Goods_Color] FOREIGN KEY([ColorId])
REFERENCES [dbo].[Goods_Color] ([Id])
GO
ALTER TABLE [dbo].[Goods] CHECK CONSTRAINT [FK_Goods_Goods_Color]
GO
ALTER TABLE [dbo].[Goods]  WITH CHECK ADD  CONSTRAINT [FK_Goods_Goods_Memory] FOREIGN KEY([MemoryId])
REFERENCES [dbo].[Goods_Memory] ([Id])
GO
ALTER TABLE [dbo].[Goods] CHECK CONSTRAINT [FK_Goods_Goods_Memory]
GO
ALTER TABLE [dbo].[Goods]  WITH CHECK ADD  CONSTRAINT [FK_Goods_Goods_Model] FOREIGN KEY([ModelId])
REFERENCES [dbo].[Goods_Model] ([Id])
GO
ALTER TABLE [dbo].[Goods] CHECK CONSTRAINT [FK_Goods_Goods_Model]
GO
ALTER TABLE [dbo].[Goods]  WITH CHECK ADD  CONSTRAINT [FK_Goods_Goods_ScreenSize] FOREIGN KEY([ScreenSizeId])
REFERENCES [dbo].[Goods_ScreenSize] ([Id])
GO
ALTER TABLE [dbo].[Goods] CHECK CONSTRAINT [FK_Goods_Goods_ScreenSize]
GO
ALTER TABLE [dbo].[Goods_Model]  WITH CHECK ADD  CONSTRAINT [FK_Goods_Model_Brands] FOREIGN KEY([BrandId])
REFERENCES [dbo].[Brands] ([Id])
GO
ALTER TABLE [dbo].[Goods_Model] CHECK CONSTRAINT [FK_Goods_Model_Brands]
GO
ALTER TABLE [dbo].[Orders_Sales]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Sales_Goods1] FOREIGN KEY([GoodsId])
REFERENCES [dbo].[Goods] ([Id])
GO
ALTER TABLE [dbo].[Orders_Sales] CHECK CONSTRAINT [FK_Orders_Sales_Goods1]
GO
ALTER TABLE [dbo].[Orders_Sales]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Sales_Users1] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Orders_Sales] CHECK CONSTRAINT [FK_Orders_Sales_Users1]
GO
USE [master]
GO
ALTER DATABASE [myOnlineShopDB] SET  READ_WRITE 
GO
