CREATE DATABASE myOnlineShopFromScript
go
USE myOnlineShopFromScript
GO

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
/****** Object:  Table [dbo].[Brands]    Script Date: 22.07.2014 14:18:39 ******/
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
/****** Object:  Table [dbo].[Goods]    Script Date: 22.07.2014 14:18:39 ******/
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
	[AmountInStock] [int] NOT NULL,
 CONSTRAINT [PK_Goods] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Goods_Color]    Script Date: 22.07.2014 14:18:39 ******/
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
/****** Object:  Table [dbo].[Goods_Memory]    Script Date: 22.07.2014 14:18:39 ******/
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
/****** Object:  Table [dbo].[Goods_Model]    Script Date: 22.07.2014 14:18:39 ******/
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
/****** Object:  Table [dbo].[Goods_ScreenSize]    Script Date: 22.07.2014 14:18:39 ******/
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
/****** Object:  Table [dbo].[Orders_Sales]    Script Date: 22.07.2014 14:18:39 ******/
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
/****** Object:  Table [dbo].[Users]    Script Date: 22.07.2014 14:18:39 ******/
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
